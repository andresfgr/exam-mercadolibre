package com.mercadolibre.exam.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@AutoConfigureTestEntityManager
public class MutantServiceTest {

	MutantService mutantService = new MutantService();

	@Test
	public void validateHumanDnaSixBySixMatrix() {
		String[] dna = { "ATGCGA", "CATCTA", "TAGTAT", "GTCTGG", "GTCACC", "ATTGCA" };
		assertFalse(mutantService.dnaParser(dna));
	}

	@Test
	public void validateHumanDnaFiveByFiveMatrix() {
		String[] dna = { "ATGCG", "CATCA", "TAGTT", "GTCTG", "GTACC" };
		assertFalse(mutantService.dnaParser(dna));
	}

	@Test
	public void validateHumanDnaFourByFourMatrix() {
		String[] dna = { "ATGC", "CATC", "TAGT", "GTCT" };
		assertFalse(mutantService.dnaParser(dna));
	}

	@Test
	public void validateMutantDnaFourByFourMatrixHorizontal() {
		String[] dna = { "AAAA", "CCCC", "GACT", "GACT" };
		assertTrue(mutantService.dnaParser(dna));
	}

	@Test
	public void validateMutantDnaFourByFourMatrixVertical() {
		String[] dna = { "GACT", "GACT", "GACT", "CTCT" };
		assertTrue(mutantService.dnaParser(dna));
	}

	@Test
	public void validateMutantDnaFiveByFiveMatrixHorizontal() {
		String[] dna = { "GACTA", "GGGGT", "GTTTT", "CGTAC", "TTGCA" };
		assertTrue(mutantService.dnaParser(dna));
	}

	@Test
	public void validateMutantDnaSixBySixMatrixVertical() {
		String[] dna = { "GACTAC", "GACTAC", "GTCTAC", "ATGCGC", "GTTCAT", "ATGCGA" };
		assertTrue(mutantService.dnaParser(dna));
	}

	@Test
	public void validateMutantDnaFourByFourMatrixOblique() {
		String[] dna = { "GACC", "AGCC", "GCGT", "CTCG" };
		assertTrue(mutantService.dnaParser(dna));
	}

	@Test
	public void validateMutantDnaSixBySixMatrixHorizontal() {
		String[] dna = { "GACTAC", "GACGTA", "AAGCTA", "GTGGGG", "GTACAG", "ATTTTA" };
		assertTrue(mutantService.dnaParser(dna));
	}

	@Test
	public void validateMutantDnaFiveByFiveMatrixVertical() {
		String[] dna = { "AATAA", "CATGC", "TATCG", "CATAC", "CCGTC" };
		assertTrue(mutantService.dnaParser(dna));
	}

	@Test
	public void validateMutantDnaFourByFourMatrixMix() {
		String[] dna = { "ATGA", "CGAC", "TAAT", "AAAA" };
		assertTrue(mutantService.dnaParser(dna));
	}

	@Test
	public void validateMutantDnaSixBySixMatrixMix() {
		String[] dna = { "ATGAGA", "CAATGC", "TAATGT", "AGAAGG", "CCGCTA", "TCATGC" };
		assertTrue(mutantService.dnaParser(dna));
	}
}
