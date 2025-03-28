/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import dao.DepartamentsDAO;
import dao.EmpleatsDAO;
import dto.Departament;
import dto.Empleat;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;
import java.util.stream.*;

/**
 *
 * @author jmas
 */
public class Main {

    public static void main(String[] args) {
        int index = 0;
        List<Departament> departamentos = DepartamentsDAO.getDepartaments();
        List<Empleat> empleados = EmpleatsDAO.getEmpleats();
        // 1.- Imprimeix el primer empleat trobat amb més de 50 anys
        ejercicioX(++index);
        empleados.stream()
                .filter(empleat -> empleat.getEdat() > 50)
                .limit(1)
                .forEach(System.out::println);
        // 2. Imprimeix true si tots els empleats son majors d'edat i false en cas contrari
        ejercicioX(++index);
        boolean mayoresDeEdad = empleados.stream()
                .allMatch(empleat -> empleat.getEdat() > 18);
        System.out.println(mayoresDeEdad);
        // 3. Imprimeix true si hi ha algun empleat major de 65 anys i false en cas contrari
        ejercicioX(++index);
        boolean mayorDe65 = empleados.stream()
                .anyMatch(empleat -> empleat.getEdat() > 65);
        System.out.println(mayorDe65);
        // 4. Imprimeix el número d'empleats que tenen més de 50 anys
        ejercicioX(++index);
        Long numEmpleats50anys = empleados.stream()
                .filter(empleat -> empleat.getEdat() > 50)
                .count();
        System.out.println(numEmpleats50anys);
        
        // 5. Imprimeix el nom dels empleats del departament d'informàtica, en majúscules.
        ejercicioX(++index);
        empleados.stream()
                .filter(empleat -> empleat.getDepartament().getNom().equalsIgnoreCase("informàtica"))
                .forEach(empleat -> System.out.println(empleat.getNom().toUpperCase()));
        
        // 6. Imprimeix la quantitat de lletres de l'empleat d'informàtica amb el nom més llarg.
        ejercicioX(++index);
        empleados.stream()
                .filter(em -> em.getDepartament().getNom().equalsIgnoreCase("informàtica"))
                .map(em -> em.getNom())
                .max(Comparator.naturalOrder())
                .map(em -> em.length())
                .ifPresentOrElse(System.out::println, () -> System.out.println("No hay empleados de informática"));

        // 7. Imprimeix el nom dels departaments que comencen per C
        ejercicioX(++index);
        departamentos.stream()
                .filter(dep -> dep.getNom().startsWith("C"))
                .forEachOrdered(System.out::println);
        
        // 8. Imprimeix la suma de totes les edats dels empleats
        ejercicioX(++index);
            System.out.println(empleados.stream()
                                    .mapToInt(em -> em.getEdat())
                                    .sum()
                                );
                    
                    
        // 9 UTILITZA EL MÈTODE GETEMPLEATS DE LA CLASSE DEPARTAMENT (NOMÉS PER A L'EXERCICI 9 I 10)
        // Imprimeix la quantitat d'empleats de cada departament
        ejercicioX(++index);
            System.out.println(departamentos.stream()
                    .map(dep -> dep.getEmpleats())
                    .count()
                    );
        
        // 10. UTILITZANT EL MÈTODE GETEMPLEATS DE LA CLASSE DEPARTAMENT (NOMÉS PER A L'EXERCICI 9 I 10)
        // Imprimeix la llista de noms dels empleats del departament comercial i de comptatilitat (una única llista d'String)
        ejercicioX(++index);
            departamentos.stream()
                    .filter(dep -> dep.getNom().equalsIgnoreCase("comercial")||dep.getNom().equalsIgnoreCase("comptabilitat"))
                    .flatMap(dep -> dep.getEmpleats().stream())
                    .map(Empleat::getNom)
                    .forEach(System.out::println);
                    
        // 11. El mateix d'abans però mostra els noms ordenats alfabèticament.
        ejercicioX(++index);
            departamentos.stream()
                    .filter(dep -> dep.getNom().equalsIgnoreCase("comercial")||dep.getNom().equalsIgnoreCase("comptabilitat"))
                    .flatMap(dep -> dep.getEmpleats().stream())
                    .map(Empleat::getNom)
                    .forEachOrdered(System.out::println);
                    
        // 12. Mostra la mitjana d'edat dels empleats del departament d'informàtica.
        ejercicioX(++index);
            empleados.stream()
                    .filter(em -> em.getDepartament().getNom().equalsIgnoreCase("informàtica"))
                    .mapToInt(Empleat::getEdat)
                    .average()
                    .ifPresentOrElse(System.out::print, ()->System.out.println("No hay empleados en el departamento de informática"));
                    
                    
        // 13. Mostra un String que serà el resultat de concatenar la primera lletra de cada departament.
        ejercicioX(++index);
            departamentos.stream()
                    .map(dep -> dep.getNom().charAt(0))
                    .map(c -> c.toString())
                    .reduce((str1,str2) -> (str1.concat(str2)))
                    .ifPresent(o -> System.out.println(o));
            
        // 14. Mostra el número de telèfon més alt dels departaments.
        ejercicioX(++index);
            departamentos.stream()
                    .sorted(Comparator.comparingInt(Departament::getTelefon).reversed())
                    .limit(1)
                    .map(Departament::getTelefon)
                    .forEach(System.out::println);
                    
        // 15. Mostra el departament complet amb el número de telèfon més alt.
        ejercicioX(++index);
            departamentos.stream()
                    .sorted(Comparator.comparingInt(Departament::getTelefon).reversed())
                    .limit(1)
                    .forEach(System.out::println);
                    ;       
        // 16. Sense fer ús del mètode "getEmpleats". Dels departament que tenen treballadors, mostrar el nom del departament i el nombre de treballadors que hi treballen. 
        ejercicioX(++index);
            empleados.stream()
                    .collect(Collectors.groupingBy(em -> em.getDepartament().getNom()))
                    .forEach((k,v)-> {
                        System.out.println("\nDepartamento: "+k);
                        System.out.println("Empleados: ");
                        v.forEach(em-> System.out.println(em.getNom()+em.getCognoms()));
                    });
                    
        // 17A. Guarda en un Map un registre per a cada Departament (que tinga treballadors) que tinga associat com a valor la llista d'empleats d'eixe departament
        ejercicioX(++index);
            final var mapaPorDepartamento = empleados.stream()
                    .collect(Collectors.groupingBy(em -> em.getDepartament()));
            
        // 17B. Igual que l'anterior pero la llista no serà d'empleats sino del nom dels empleats
        ejercicioX(++index);
            final var mapaDepConNomEmp = empleados.stream()
                    .collect(Collectors.groupingBy(Empleat::getDepartament,
                                    Collectors.mapping(Empleat::getNom, 
                                            Collectors.toList())
                            ));
            
        // 17C. Igual que l'anterior pero amb els empleats ordenats alfabèticament
        ejercicioX(++index);
            final var mapaDepConNomEmpNuevo = empleados.stream()
                    .collect(Collectors.groupingBy(Empleat::getDepartament,
                                    Collectors.mapping(Empleat::getNom, 
                                            Collectors.collectingAndThen(
                                                    Collectors.toList(), list -> { Collections.sort(list);
                                                                                    return list; }))));
            
        // 18A. Obtín un Map que continga per a cada departament una llista d'enters grans (BigInteger) que es corresponguen amb les edats dels empleats d'eixe departament
        ejercicioX(++index);
            empleados.stream()
                    .collect(Collectors.groupingBy(Empleat::getDepartament,
                                    Collectors.mapping(em -> new BigInteger(Integer.toString(em.getEdat())) , Collectors.toList())));//hacer un forEach para comprobar si está yendo bien
            
        // 18B. El mateix que abans però en comptes de l'edat, el probable següent número primer
        ejercicioX(index);
            empleados.stream()
                    .collect(Collectors.groupingBy(Empleat::getDepartament, 
                                    Collectors.mapping(em -> BigInteger.valueOf(em.getEdat()).nextProbablePrime(), Collectors.toList())));
        // 19. Obtín un Map que continga per a cada departament l'empleat més jove.
        ejercicioX(++index);
            empleados.stream()
                    ;
                    //groupingBy()
        // 20. Obtín un String que continga el mateix que abans, amb el format "Departament1:Empleat1, Departament2:Empleat2 ..."
        // 21. Obtín un Map que conté com a clau el DNI dels empleats i com a valor el nom d'eixe empleat
        // 21. Obtín una llista d'Strings que continga DNI dels empleats i el nom d'eixe empleat amb el format DNI - Nom. Llista ordenada per DNI
        // 22A. Donat un array bidimensional d'Integer transformar-lo en un array unidimensional amb els mateixos valors:
        Integer[][] matriuInteger = {{3, 2, 5}, {0, -8, 7}, {9, 9, 6}};

        // 22B. Versió amb int[] en comptes d'Integer[]
        // 23. Guarda una llista amb els noms dels estudis de tots els empleats (de forma única), ordenats alfabèticament.
        // 24. Imprimeix per pantalla aquells empleats que tinguen un CFGS
        // 25. Mostra un empleat qualsevol que tinga una llicenciatura
        // 26. Mostra per a cada nom d'empleat la suma de les lletres dels seus titols
        // 27. Mostra el total de lletres dels titols (incloent duplicats) dels empleats
        // 28. Mostra l'empleat amb major quantitat de títols
        // 29A. Obtín una llista que continga 5 aleatoris entre 0 i 9 en format String
        // 29B. El mateix d'abans, però ara sense valors repetits.
        // 30. Crea un stream amb tots els números positius divisibles entre 3. Després filtra aquells que siguen quadrats perfectes. 
        // Després descarta els 5 primers valors obtinguts. Finalment mostra els 10 següents valors obtinguts
        // 31. A partir dels cognoms dels empleats imprimeix amb una operació intermèdia els cognoms sense transformar i després transformats en majúscules.
        // Finalment retorna el nombre d'empleats.
        // 32. Imprimeix el cognom dels empleats ordenats en ordre alfabètic invers.
        // 33. Obtín la suma dels títols de tots els empleats
        // 34. Obtín un String amb la concatenació dels títols d'aquells empleats que tinguen menys de 30 anys, separats per espais (sense duplicats)
        // 35A. Mostra el cognom del primer dels empleats trobat que tinga menys de 30 anys i que tinga una llicenciatura. En cas de no trobar-lo mostra "No trobat"
        // ¿I si proves per a menors de 40?
        // 35B. Mostra el primer dels empleats (complet) trobat que tinga menys de 30 anys i que tinga una llicenciatura. En cas de no trobar-lo mostra "No trobat"
        // ¿I si proves per a menors de 40?
        // 36. Obtín un LinkedHashSet dels títols que tenen tots els empleats.
        // 37. Obtín un Map amb dos claus, la primera tindrá com a valors una llista dels empleats amb el títol de CFGS i l'altra clau tindrá una llista amb els que no el tenen.
        // 38. A partir d'una llista d'String retorna la mateixa llista pero sense cadenes buides
        List<String> elementsOriginals = List.of("aigua", "", "llet", "oli", "", "  ", "lletuga");

        // 39. Obtín una cadena separada per comes basada en una llista determinada d'enters. 
        // Cada element ha d'anar precedit de la lletra 'p' si el nombre és parell i precedit de la lletra 'i' si el nombre és imparell. 
        // Per exemple, si la llista d'entrada és (3,44), la eixida hauria de ser 'i3, p44'.
        List<Integer> llistaEnters = List.of(9, 23, 4, 15);

        // 40.A partir dels empleats. Obtín un mapa que tinga com a clau el nom del departament i com a valor un altre mapa
        // Este segon mapa tindrà com a clau el cognom de l'empleat y com a valor la llista de títols que té.
        // EXTRA A: A partir d'un String retorna les lletres distintes que conté, separades per comes (només lletres, no espais ni números ni altres caracters)
        String frase1 = "Tinc un 8 en PROG";

        // EXTRA B: A partir d'un String retorna la quantitat de lletres distintes que conté (només lletres, no espais ni números ni altres caracters)
        // EXTRA C: Obtín un mapa que mostre com a clau cada lletra distinta del String, i com a valor la quantitat de vegades que apareix.
        // EXTRA D: Mostra la lletra amb major freqüencia d'aparicions, i el nombre d'aparicions d'eixa lletra.
    }

    public static void ejercicioX(int index) {
        System.out.println("--- Ejercicio " + (index) + " ---");
    }
}
