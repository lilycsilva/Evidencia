import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import java.util.*;

public class ProyectoFinal{
	
	private static String[] pacientes = new String[100000];
	private static int size_pacientes;
	
	private static String[][] doctores = new String[2][10000];
	private static int size_doctor;
	
	private static String[] admins = new String[10000];
	private static int size_admin = 1;
	
	private static boolean[][][] disponibilidad = new boolean [31][24][10000];
	
	
	public static int alta_paciente(String nombre) {
		pacientes [size_pacientes] = nombre;
		size_pacientes += 1;
		
		return 30000 + size_pacientes - 1;
		
	}
	
	public static int alta_doctor(String nombre, String especialidad) {
		doctores[0][size_doctor] = nombre;
		doctores[1][size_doctor] = especialidad;
		size_doctor += 1;
		
		return 10000 + size_doctor - 1;
	}
	
	public static int alta_admin(String password) {
		admins [size_admin] = password;
		size_admin += 1;
		
		return 20000 + size_admin - 1;
	}
	
	public static void ingresar() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Ingresa tu numero de administrador");
		

		int pos = 20000 - sc.nextInt();
		
		System.out.println("Ingresa tu contraseña");
		
		String pass = sc.next();
		
		if(admins[pos].contentEquals(pass)) {
			System.out.println("Bienvenido, has ingresado al sistema");
			menu1();
			
			
		}else {
			System.out.println("El usuario o contraseña son incorrectos, intente de nuevo");
			ingresar();
		}
		
	}
	private static void cita(int id_paciente, int id_doctor, int dia, int hora, String motivo) {
		int pos_doc = 10000 - id_doctor;
		if(disponibilidad[dia][hora][pos_doc] == false) { //tiene disponibilidad
			System.out.println("Se ha generado la cita para el "
					+ "paciente " + id_paciente + "con el doctor " + id_doctor + "el "
							+ " dia " + dia + " a las " + hora + " horas con el motivo: " +motivo);
			disponibilidad[dia][hora][pos_doc] = true; //cambia a ocupado
			menu1();
		}else {
			System.out.println("No se pudo generar la cita en este horario, intente con otro");
			menu1();
		}
		
		
	}
	
	private static void descarga_doctor() {
		File f;
	    FileWriter w;
	    BufferedWriter bw;
	    PrintWriter wr;
	    
	    try{
            f = new File("Doctores");
            w = new FileWriter(f);
            bw = new BufferedWriter(w);
            wr= new PrintWriter(bw);
            
           for(int i = 0; i < size_doctor; i++) {
        	   bw.write(doctores[i] +"");
        	   for(int j = 0; j < size_doctor; j++) {
        		   bw.write(doctores[j] +"");
        	   }
        	   
           }
            wr.close();
            bw.close();
            menu1();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error" + e);
            menu1();
        }
		
		
	}
	
	private static void descarga_admin() {
		File f;
	    FileWriter w;
	    BufferedWriter bw;
	    PrintWriter wr;
	    
	    try{
            f = new File("Administradores");
            w = new FileWriter(f);
            bw = new BufferedWriter(w);
            wr= new PrintWriter(bw);
            
           for(int i = 0; i < size_admin; i++) {
        	   bw.write(20000+i + " " + admins[i] +"\n");
        	   
        	   
           }
            wr.close();
            bw.close();
            menu1();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error" + e);
            menu1();
        }
		
		
	}
	private static void descarga_pacientes() {
		File f;
	    FileWriter w;
	    BufferedWriter bw;
	    PrintWriter wr;
	    
	    try{
            f = new File("Pacientes");
            w = new FileWriter(f);
            bw = new BufferedWriter(w);
            wr= new PrintWriter(bw);
            
           for(int i = 0; i < size_pacientes; i++) {
        	   bw.write(30000+i + " " + pacientes[i] +"\n");
        	   
        	   
           }
            wr.close();
            bw.close();
            menu1();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error" + e);
            menu1();
        }
	    
		
		
	}

	private static void menu1() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("¿Qué deseas hacer?");
		System.out.println("1. Hacer una cita \n"
				+ "2. Dar de alta a un paciente \n"
				+ "3. Dar de alta a un doctor \n"
				+ "4. Dar de alta a un administrador\n"
				+ "5. Descargar archivo de alta de doctores\n"
				+ "6. Descargar archivo de alta de administradores \n"
				+ "7. Descargar archivo de alta de pacientes \n"
				+ "8. Cerrar sesion");
		
		int opcion = sc.nextInt();
		
		if(opcion == 1) {
			//pedir datos para cita
			System.out.println("id del paciente: ");
			int id_paciente = sc.nextInt();
			System.out.println("id del doctor: ");
			int id_doctor = sc.nextInt();
			System.out.println("Dia: ");
			int dia = sc.nextInt();
			System.out.println("Hora: ");
			int hora = sc.nextInt();
			System.out.println("Motivo: ");
			String motivo = sc.next();
			cita(id_paciente, id_doctor, dia, hora, motivo);
			menu1();
			
		}else if(opcion == 2) {
			System.out.println("Ingrese el nombre del paciente: ");
			String nombre = sc.next();
			System.out.println("El paciente se ha registrado correctamente, su id es: " + alta_paciente(nombre));
			menu1();
			
		}else if(opcion == 3) {
			System.out.println("Ingrese el nombre del doctor: ");
			String nombre = sc.next();
			System.out.println("Ingrese la especialidad del doctor: ");
			String especialidad = sc.next();
			System.out.println("El doctor se ha registrado correctamente, su id es: " + alta_doctor(nombre, especialidad));
			menu1();
			
		}else if(opcion == 4) {
			System.out.println("Ingrese la contraseña del nuevo administrador: ");
			String password = sc.next();
			System.out.println("El nuevo admin se ha registrado correctamente, "
					+ "su id es: " + alta_admin(password) + " y la contraseña es: " + password);
			menu1();
			
		}else if(opcion == 5) {
			descarga_doctor();
			
		}else if(opcion == 6) {
			descarga_admin();
			
		}else if(opcion == 7) {
			descarga_pacientes();
			
		}else if (opcion == 8) {
			System.exit(0);
			
		}else {
			menu1();
		}
	}

	public static void main(String[] args) {
		admins[0] = "password";
		ingresar();
	}

}