package algorithm.logicAC_VRP_SPDVCFP;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXParseException;

import algorithm.FireflyAlgorithmAC_VRP_SPDVCFP.Prohibicion;


public class LectorXML extends DefaultHandler{

	private GestorEstaciones problem = GestorEstaciones.getInstancia();
	private Node e;
	//private ArrayList<Estacion_secundaria> l_secundarias;
	private String texto;
	protected int id;
	protected double coordX;
	protected double coordY;
	protected int dem;
	protected int demRec;
	protected int cluster;
	protected int pro1;
	protected int pro2;
		
	public void startDocument() throws SAXException {
		//System.out.println("# Comienzo del parseo");
		this.texto = "";
	}

	public void endDocument() throws SAXException {
		//System.out.println("# Fin del parseo");
		/*for(int i=0; i<problem.getL_ciudades().size();i++){
			for(int j=0; j<problem.getL_ciudades().size();j++){
				System.out.print(problem.getM1()[i][j] + " - ");
			}
			System.out.println();
		}*/
		
	}
	
	public void startElement(String namespaceURI, String lName, // local name
			String qName, // qualified name
			Attributes attrs) throws SAXException {
		if (qName.equals("NodeP")) {
			e = new Node();
			//l_secundarias = new ArrayList<Estacion_secundaria>();
			//e.setIdentificador(gEst.getL_est().size());
		}
		
	}

	public void endElement(String namespaceURI, String lName, String qName)
			throws SAXException {
		if (this.texto.length() > 0) {
			if (qName.equals("Addr")) {
				this.e.setDireccion(this.texto);
			}else if (qName.equals("id")) {
				this.id= Integer.parseInt(this.texto);
				this.e.setIdentificador(id);
			}else if (qName.equals("DemEnt")) {
				this.dem= Integer.parseInt(this.texto);
				this.e.setDemanda(dem);
			}else if (qName.equals("DemRec")) {
				this.demRec= Integer.parseInt(this.texto);
				this.e.setDemandaRecogida(demRec);
			}else if (qName.equals("Cluster")) {
				this.cluster= Integer.parseInt(this.texto);
				this.e.setCluster(cluster);
			}else if (qName.equals("CoordX")) {
				this.coordX= Double.parseDouble(this.texto);
				this.e.setCoordX(coordX);
			}else if (qName.equals("CoordY")) {
				this.coordY= Double.parseDouble(this.texto);
				this.e.setCoordY(coordY);
				problem.añadirEstacion(e);
			}else if (qName.equals("Separacion")) {
				problem.setProhibiciones(new ArrayList<Prohibicion>());	
			}else if (qName.equals("est1")) {
				this.pro1= Integer.parseInt(this.texto);
			}else if (qName.equals("est2")) {
				this.pro2= Integer.parseInt(this.texto);
				problem.añadirProhibicion(new Prohibicion(pro1,pro2));
			}		
		}
		this.texto = "";
	}
	
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String s = String.valueOf(ch, start, length).trim();
		this.texto += s;
		}
		
		public void processingInstruction(String target, String data)
			throws SAXException {
		System.out.println(" * [PI] -> <?" + target + data + "?>");
		}
	
	public void error(SAXParseException exception) throws SAXException {
		System.out.println("# [ERROR] -> " + exception.getMessage());
	}
	
	public void warning(SAXParseException exception) throws SAXException {
		System.out.println("# [WARNING] -> " + exception.getMessage());
	}
	
	public void fatalError(SAXParseException exception) throws SAXException {
		System.out.println("# [FATAL ERROR] -> " + exception.getMessage());
	}

}
