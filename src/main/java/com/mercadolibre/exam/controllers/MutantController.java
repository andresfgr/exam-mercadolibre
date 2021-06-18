package com.mercadolibre.exam.controllers;

import java.util.ArrayList;

import com.mercadolibre.exam.entities.Mutant;
import com.mercadolibre.exam.models.MutantModel;
import com.mercadolibre.exam.services.MutantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * UsuarioController
 */
@RestController
@RequestMapping("/mutant")
public class MutantController {
    @Autowired
    MutantService service;

	//Obtiene todos los registros analizados
    @GetMapping()
    public ArrayList<MutantModel> allMutants() {
        return (ArrayList<MutantModel>) service.allMutants();
    }

	//Obtiene todos los registros filtrados por el parametro isMutant
    @GetMapping("/query")
    public ArrayList<MutantModel> allMutantsIsMutant(@RequestParam("isMutant") Boolean isMutant) {
        return (ArrayList<MutantModel>) service.allMutantsByIsMutant(isMutant);
    }

	//Valida si es Homano o Mutante
    @PostMapping()
	@ResponseBody
    public ResponseEntity<String> isMutant(@RequestBody Mutant mutante) throws Exception {

		String[] dna = mutante.getDna().toArray((new String[0]));
		boolean isMutant = service.isMutant(dna);

		ResponseEntity<String> response;

		if (isMutant) {
			response = new ResponseEntity<String>(HttpStatus.OK);
		} else {
			response = new ResponseEntity<String>(HttpStatus.FORBIDDEN);
		}
		return response;
    }
}