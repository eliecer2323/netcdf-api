package co.edu.udistrital.informatica.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ucar.netcdf.Netcdf;
import ucar.netcdf.NetcdfFile;
import ucar.netcdf.Variable;

public class ArchivoNetcdf {
	
	private Netcdf file;
	private List datos;
	
	public ArchivoNetcdf(String fileName) throws IOException {
		file = new NetcdfFile(fileName, true);
	}
	
	public void iterar(Variable var, int[] ix, int index, List data) {
		try {
			for (int i = 0; i < var.getLengths()[index]; i++) {
				ix[index] = i;
				if (index < ix.length - 1) {
					List subList = new ArrayList();
					iterar(var, ix, index + 1, subList);
					data.add(subList);
				} else {
					data.add(var.get(ix));
				}
			}
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}
	
	public List cargarVar(String varName) throws IOException {
		Variable var = file.get(varName);
		int[] nVar = var.getLengths();
		int[] ix = new int[nVar.length];
		datos = new ArrayList();

		if (nVar.length == 0) {
			datos.add(var.get(nVar));
		} else if (nVar.length > 0) {
			iterar(var, ix, 0, datos);
		}
		return datos;
	}
	
	public int[] getVarLengths(String varName) {
		Variable var = file.get(varName);
		int[] nVar = var.getLengths();
		return nVar;
	}
	
	public Netcdf getFile() {
		return file;
	}

}
