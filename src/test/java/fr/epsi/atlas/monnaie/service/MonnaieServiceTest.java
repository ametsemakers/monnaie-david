package fr.epsi.atlas.monnaie.service;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;
import org.mockito.Mockito;

import fr.epsi.atlas.monnaie.repository.MonnaieRepository;

public class MonnaieServiceTest {

	@Test
	public void deleteByCodeQuandLaMonnaieExiste() throws Exception {
		MonnaieRepository mockRepository = Mockito.mock(MonnaieRepository.class);
		Mockito.when(mockRepository.existsById("USD")).thenReturn(true);
		
		MonnaieService sut = new MonnaieService(mockRepository);
		
		sut.deleteByCode("USD");
		
		Mockito.verify(mockRepository).deleteById("USD");
	}
	
	@Test(expected = MonnaieInexistanteException.class)
	public void getByCodeUneExceptionLorsqueLeCodeNeCorrespondPasAUnneMonnaie() throws Exception {
		MonnaieRepository mockRepository = Mockito.mock(MonnaieRepository.class);
		MonnaieService sut = new MonnaieService(mockRepository);
		Mockito.when(mockRepository.findById("USD")).thenReturn(Optional.empty());
		
		sut.getByCode("USD");
	}
}
