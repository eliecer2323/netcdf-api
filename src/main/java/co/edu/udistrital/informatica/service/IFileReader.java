package co.edu.udistrital.informatica.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IFileReader {

	List<String[]> readNetCDF() throws IOException;

	List ReadVariable(String varName) throws IOException ;

}
