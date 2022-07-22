package com.example.bulletinboardapi;

import com.example.bulletinboardapi.DTO.AdRequestDTO;
import com.example.bulletinboardapi.DTO.AdResponseDTO;
import com.example.bulletinboardapi.exceptions.AdIdNotFoundException;
import com.example.bulletinboardapi.model.Ad;
import com.example.bulletinboardapi.repository.AdRepository;
import com.example.bulletinboardapi.service.impl.AdServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@SpringBootTest
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
class AdBoardApiApplicationTests {

	@Autowired
	private AdServiceImpl adService;

	@MockBean
	private AdRepository adRepository;

	@Test
	public void createNewAd_validRequest_adCreated() {
		AdRequestDTO adRequestDTO = new AdRequestDTO("Title", "Content");
		when(adRepository.save(any())).then((Answer<Ad>) invocationOnMock -> {
			Ad ad = invocationOnMock.getArgument(0);
			ad.setId(generateRandomId());
			return ad;
		});

		AdResponseDTO adDto = adService.createAd(adRequestDTO);

		assertThat(adDto.getId()).isNotNull();
		assertThat(adDto.getTitle()).isEqualTo(adRequestDTO.getTitle());
		assertThat(adDto.getContent()).isEqualTo(adRequestDTO.getContent());
	}

	@Test
	public void findAdById_randomId_returnedIdCorrect() {
		Long adId = generateRandomId();
		when(adRepository.findByIdOrThrow(any())).then(invocationOnMock -> createAd(invocationOnMock.getArgument(0)));

		AdResponseDTO adResponseDTO = adService.findById(adId);

		assertThat(adResponseDTO.getId()).isEqualTo(adId);
	}

	@Test
	public void deleteAdById_existingId_adDeleted() {
		Long adId = generateRandomId();
		Collection<Long> removedIds = new HashSet<>();
		when(adRepository.findByIdOrThrow(any())).then(invocationOnMock -> createAd(invocationOnMock.getArgument(0)));
		doAnswer(invocationOnMock -> {
			Long passedAdId = invocationOnMock.getArgument(0);
			if (removedIds.contains(passedAdId)) {
				throw new AdIdNotFoundException(passedAdId);
			}
			removedIds.add(passedAdId);
			return passedAdId;
		}).when(adRepository).deleteById(any());

		AdResponseDTO adDto = adService.deleteAd(adId);

		assertThat(adDto).isNotNull();
		assertThat(adDto.getId()).isEqualTo(adId);
		assertThat(removedIds).containsExactly(adId);

		Mockito.verify(adRepository, Mockito.times(1)).deleteById(any());
	}

	private long generateRandomId() {
		return new Random(System.currentTimeMillis()).nextLong(1, Long.MAX_VALUE);
	}

	private Ad createAd(long id, String title, String content) {
		Ad ad = new Ad();
		ad.setId(id);
		ad.setTitle(title);
		ad.setContent(content);
		return ad;
	}

	private Ad createAd(long id) {
		return createAd(id, "Title", "Content");
	}

}
