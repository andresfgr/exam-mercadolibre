package com.mercadolibre.exam.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.mercadolibre.exam.models.MutantModel;
import com.mercadolibre.exam.repositories.MutantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantService {

    @Autowired
    MutantRepository repository;

	//Obtiene todos los registros analizados
    public ArrayList<MutantModel> allMutants() {
        return (ArrayList<MutantModel>) repository.findAll();
    }

	//Obtiene todos los registros filtrados por el parametro isMutant
    public ArrayList<MutantModel> allMutantsByIsMutant(boolean isMutant) {
        return (ArrayList<MutantModel>) repository.findByIsMutant(isMutant);
    }

	//Valida si es Homano o Mutante y ejecuta el guardado del registro
	public boolean isMutant(String[] dna) {

		boolean isMutant;
		try {

			String dnaString = Arrays.toString(dna);
			MutantModel usuarioModel = repository.findByDna(dnaString);

			if (usuarioModel != null) {
				isMutant = usuarioModel.getIsMutant();
			} else {
				isMutant = this.dnaParser(dna);
				usuarioModel = new MutantModel() ;
				usuarioModel.setDate(new Date());
				usuarioModel.setIsMutant(isMutant);
				usuarioModel.setDna(dnaString);
				repository.save(usuarioModel);
			}
		} catch (Exception e) {
			throw e;
		}

		return isMutant;
	}

	//Recorre la secuencia de adn para comprobar si es Humano o Mutante
	public boolean dnaParser(String[] dna) {
		int mutantDna = 0;

		try {
			for (int i = 0; i < dna.length; i++) {
				for (int j = 0; j < dna[i].length(); j++) {

					// Validar combinaciones en forma horizontal
					if (j < dna[i].length() - 3) {

						if (areEqualDna(dna[i].charAt(j), dna[i].charAt(j + 1), dna[i].charAt(j + 2),
								dna[i].charAt(j + 3))) {
							mutantDna++;
						}
					}

					// Validar combinaciones en forma Vertical
					if (i < dna[i].length() - 3) {
						if (areEqualDna(dna[i].charAt(j), dna[i + 1].charAt(j), dna[i + 2].charAt(j),
								dna[i + 3].charAt(j))) {
							mutantDna++;
						}
					}

					// Validar combinaciones en forma Oblicua, Derecha a izquierda & Arriba a abajo
					if (j < dna[i].length() - 3 && i < dna[i].length() - 3) {
						if (areEqualDna(dna[i].charAt(j), dna[i + 1].charAt(j + 1),
								dna[i + 2].charAt(j + 2), dna[i + 3].charAt(j + 3))) {
							mutantDna++;
						}
					}

					// Validar combinaciones en forma Oblicua, Izquierda a derecha & Abajo a arriba
					if (dna[i].length() > 3 && j < dna[i].length() - 3 && i > 2) {
						if (areEqualDna(dna[i].charAt(j), dna[i - 1].charAt(j + 1),
								dna[i - 2].charAt(j + 2), dna[i - 3].charAt(j + 3))) {
							mutantDna++;
						}
					}

					if (mutantDna >= 2) {
						return true;
					}
				}

			}

		} catch (Exception e) {
			throw e;
		}

		return false;
	}

	//Valida que dada 4 letras si son iguales o no
	public static boolean areEqualDna(char a, char b, char c, char d) {
		return a == b & b == c & c == d;
	}
}