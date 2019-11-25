package co.edu.udistrital.informatica.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.udistrital.informatica.service.IFileReader;

@RestController
@RequestMapping("/data")
public class DataController {

	@Autowired
	private IFileReader fileReader;

	@CrossOrigin
	@GetMapping("/all")
	public List<String[]> getAll() throws IOException {
		return fileReader.readNetCDF();
	}

	@CrossOrigin
	@GetMapping("/variable/{name}")
	public List getVariable(@PathVariable(value = "name") String varName) throws IOException {
		return fileReader.ReadVariable(varName);
	}
	
}
