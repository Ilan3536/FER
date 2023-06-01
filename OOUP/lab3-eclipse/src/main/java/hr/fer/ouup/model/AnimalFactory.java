package hr.fer.ouup.model;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.util.*;


public class AnimalFactory {

	
	private static Map<String, Animal> animals;
	
	@SuppressWarnings("unchecked")
	public static Animal newInstance(String animalKind, String name) {
		
		if (animals == null) {
			animals = new HashMap<String, Animal>();
		}
		
		Animal instance;
		if ((instance = animals.get(animalKind)) != null) {
			return instance;
		}
		
		Class<Animal> clazz = null;
		try {
			//clazz = (Class<Animal>)Class.forName("hr.fer.ooup.model.plugins."+animalKind);
			
			if (clazz == null) {
				ClassLoader parent = AnimalFactory.class.getClassLoader();

				URLClassLoader newClassLoader = new URLClassLoader(
					new URL[] {
						// Dodaj jedan direktorij (završava s /)
						new File("C:/java/plugins/").toURI().toURL(),
						// Dodaj jedan konkretan JAR (ne završava s /)
						new File("C:/java/plugins-jarovi/zivotinje.jar").toURI().toURL()
					}, parent);
				
				clazz = (Class<Animal>)newClassLoader.loadClass("hr.fer.zemris.ooup.lab2.model.plugins."+animalKind);

			}
			Constructor<?> ctr = clazz.getConstructor(String.class);
			Animal animal = (Animal)ctr.newInstance(name);
			return animal;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}