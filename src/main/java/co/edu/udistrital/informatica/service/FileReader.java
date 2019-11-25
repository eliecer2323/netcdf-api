package co.edu.udistrital.informatica.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import co.edu.udistrital.informatica.core.ArchivoNetcdf;
import ucar.netcdf.DimensionIterator;
import ucar.netcdf.Variable;
import ucar.netcdf.VariableIterator;

@Service
public class FileReader implements IFileReader {
	
	private String filePath = "src/main/resources/outN.netcdf";

	public List<String[]> readNetCDF() throws IOException {
		ArchivoNetcdf nc = new ArchivoNetcdf(filePath);
		VariableIterator vi = nc.getFile().iterator();
		List<String[]> variables = new ArrayList<String[]>();
		while (vi.hasNext()) {
			Variable var = vi.next();
			String descripcion = var.getAttribute("long_name") != null ? var.getAttribute("long_name").getStringValue() : "";
			String unidades = var.getAttribute("units") != null ? var.getAttribute("units").getStringValue() : "";
			String nombre = var.getName();
			int[] sombra = var.getLengths();
			StringBuilder sombraText = new StringBuilder();
			for (int i = 0; i < sombra.length; i++) {
				sombraText.append(sombra[i]);
				if (i < sombra.length - 1) {
					sombraText.append(", ");
				}
			}
			DimensionIterator di = var.getDimensionIterator();
			StringBuilder dimensiones = new StringBuilder();
			while (di.hasNext()) {
				ucar.netcdf.Dimension dimension = di.next();
				dimensiones.append(dimension.getName());
				if (di.hasNext()) {
					dimensiones.append(", ");
				}
			}
			String[] rowData = { var.getComponentType().getName(), descripcion, dimensiones.toString(), nombre, sombraText.toString(), unidades };
			variables.add(rowData);
		}
		return variables;
	}

	public List ReadVariable(String varName) throws IOException {
		ArchivoNetcdf nc = new ArchivoNetcdf(filePath);
		List dataVar = nc.cargarVar(varName);
		
		return dataVar;
	}
	

}
