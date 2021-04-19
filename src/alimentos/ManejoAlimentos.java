package alimentos;

import sun.font.TrueTypeFont;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManejoAlimentos extends JFrame implements ActionListener, ChangeListener {

    private final Color COLORES[] = {Color.GREEN, Color.YELLOW, Color.ORANGE, Color.PINK, Color.MAGENTA};
    private final String TIPO_ALIMENTO[] = {"VERDURAS", "FRUTAS","CEREALES","LEGUMINOSAS", "ALIM. DE ORIG. ANIMAL"};
    private final int KCAL_TIPO_ALIM[] = {25,60,70,120,40}; //kilocalorias por ración por tipo de alimento
    private JSlider racTipoAlim[]; //Fija el numero de raciones por tipo de alimento
    private JLabel kCalTipoAlim[]; //Guarda las kilocalorias por tipo de alimento
    private JPanel pKcalTipoAlim[]; //Paneles para guardar las barras a desplegar
    private JLabel kilocalorias; //Para mostrar el valor de las kilocalorias
    private JButton verificar;

    private Alimentos alimentos[];

    public ManejoAlimentos (){

        super("Práctica	2");
        Container panel	=	getContentPane();
        JTabbedPane	panelPrincipal	=	new	JTabbedPane();
        panelPrincipal.addTab("Conociendo	los	alimentos	",panelConociendo());
        //panelPrincipal.addTab("Elección	de	alimentos",	panelAlimentos());
        //	panelPrincipal.addTab("Clasificación	de	alimentos",	grafico);
        panel.add(panelPrincipal);
        this.setSize(750,400);
        this.setLocation(200,200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cargarDatos();
    }

    public JPanel panelConociendo(){

        //panel	de	conociendo	los	alimentos
        JPanel	conocer	=	new	JPanel();
        conocer.setLayout(new BorderLayout());

        JPanel	panelSuperior	=	new	JPanel();
        panelSuperior.setLayout(new	BorderLayout());

        JPanel	panelCentro		=	new	JPanel();
        panelCentro.setLayout(new GridLayout());

        JPanel	panelSur	    =	new	JPanel();
        JPanel	panelTitulo		=	new	JPanel();
        JPanel	panelEncabezados=	new	JPanel();
        panelTitulo.setLayout(new GridLayout(0,1));
        JLabel	enc1=	new	JLabel("CONOCIMIENTO DE LAS KILOCALORIAS APORTADAS	POR	RACIONES POR TIPO DE ALIMENTO");
        JLabel	enc2=	new	JLabel("Elige el número las raciones de cada tipo de alimento que consideres debes	de consumir");

        panelSuperior.add(enc1,BorderLayout.NORTH);
        panelSuperior.add(panelEncabezados,BorderLayout.WEST);
        panelSuperior.add(panelTitulo, BorderLayout.CENTER);
        panelSuperior.add(enc2,BorderLayout.SOUTH);

        pKcalTipoAlim	=	new	JPanel[TIPO_ALIMENTO.length];//Atributo
        JPanel	pGpoTipoAlim	=	new	JPanel(); //	sirve	para	agrupar	a	los	JSlider	y	las	JLabel	(barras)
        pGpoTipoAlim.setLayout(new BorderLayout()); //	Te	toca	especificar	el	tipo	de	distribución(administrador)
        racTipoAlim	=	new	JSlider[TIPO_ALIMENTO.length];
        kCalTipoAlim		=	new	JLabel[TIPO_ALIMENTO.length];
        verificar = new JButton("Verifica proporciòn"); // Debes	declararlo como atributo
        kilocalorias	=	new	JLabel("Total de kilocalorias: 0");
        panelSur.add(kilocalorias);
        panelSur.add(verificar);
        //panelSur.add(reAlimentacion);

        for(int ta=0; ta < TIPO_ALIMENTO.length ; ta++ ){
            JPanel pDatos = new JPanel();
            pDatos.setLayout(new BorderLayout());
            JLabel tit = new JLabel(TIPO_ALIMENTO[ta]);
            tit.setHorizontalAlignment(SwingConstants.CENTER);
            pKcalTipoAlim[ta] = new JPanel();
            racTipoAlim[ta] = new JSlider();
            racTipoAlim[ta].setOrientation(SwingConstants.VERTICAL);
            racTipoAlim[ta].setPaintLabels(true);
            racTipoAlim[ta].setPaintTicks(true);
            racTipoAlim[ta].setMinimum(0);
            racTipoAlim[ta].setMaximum(10);
            racTipoAlim[ta].setValue(0);
            racTipoAlim[ta].setMajorTickSpacing(2);
            racTipoAlim[ta].setMinorTickSpacing(1);

            kCalTipoAlim[ta] = new JLabel();
            kCalTipoAlim[ta].setBackground(COLORES[ta]);
            kCalTipoAlim[ta].setOpaque(true);
            kCalTipoAlim[ta].setVerticalAlignment(SwingConstants.CENTER);
            kCalTipoAlim[ta].setHorizontalAlignment(SwingConstants.CENTER);

            pKcalTipoAlim[ta].setLayout(new BorderLayout());
            pKcalTipoAlim[ta].setPreferredSize(kCalTipoAlim[ta].getPreferredSize());
            pKcalTipoAlim[ta].add(kCalTipoAlim[ta]);

            pDatos.add(tit,BorderLayout.NORTH);
            pDatos.add(racTipoAlim[ta],BorderLayout.WEST);
            pDatos.add(pKcalTipoAlim[ta],BorderLayout.CENTER);
            pGpoTipoAlim.add(pDatos);

            racTipoAlim[ta].addChangeListener(this);

        }
        panelCentro.add(pGpoTipoAlim);
        conocer.add(panelCentro);
        conocer.add(panelSur,BorderLayout.SOUTH);

        verificar.addActionListener(this);

        conocer.add(panelSuperior,BorderLayout.NORTH);
        return conocer;


    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object	prod=	ae.getSource(); //	Productor	del	evento
        if(prod == verificar)
        {	String	mensaje="Hola";	//	agrega	el	texto	necesario, como	se	muestra en	el apartado	de	especificacion
                JOptionPane.showMessageDialog(this,mensaje);
        }
    }
    @Override
    public void stateChanged(ChangeEvent ce) {
        int totalkc = 0;
        for(int ta=0; ta <COLORES.length; ta++){
            int base = (racTipoAlim[ta].getHeight()-racTipoAlim[ta].getValue()*KCAL_TIPO_ALIM[ta])*racTipoAlim[ta].getHeight()/racTipoAlim[ta].getHeight();
            kCalTipoAlim[ta].setBounds(0,base,60,racTipoAlim[ta].getHeight()*racTipoAlim[ta].getValue()*KCAL_TIPO_ALIM[ta]/racTipoAlim[ta].getHeight());
            kCalTipoAlim[ta].setText(""+racTipoAlim[ta].getValue()*KCAL_TIPO_ALIM[ta]+"-KC");
            totalkc += racTipoAlim[ta].getValue()*KCAL_TIPO_ALIM[ta];
        }
        kilocalorias.setText("Total KC: "+totalkc+" ");
        this.repaint();
    }


    //public JPanel panelAlimentos(){ }

    public static void main(String args[]){
        ManejoAlimentos alimentos = new ManejoAlimentos();
    }

    private void cargarDatos(){

        alimentos = new Alimentos[22];
        alimentos[0] = new Alimentos("Zanahorias",0);
        alimentos[1] = new Alimentos("Lechuga",0);
        alimentos[2] = new Alimentos("Espinaca",0);
        alimentos[3] = new Alimentos("Acelgas",0);
        alimentos[4] = new Alimentos("Tomate",0);
        alimentos[5] = new Alimentos("Platano",1);
        alimentos[6] = new Alimentos("Naranja",1);
        alimentos[7] = new Alimentos("Manzana",1);
        alimentos[8] = new Alimentos("Melon",1);
        alimentos[9] = new Alimentos("Arroz",2);
        alimentos[10] = new Alimentos("Pan Blanco",2);
        alimentos[11] = new Alimentos("Avena",2);
        alimentos[12] = new Alimentos("Tortilla",2);
        alimentos[13] = new Alimentos("Frijol",3);
        alimentos[14] = new Alimentos("Cacahuate",3);
        alimentos[15] = new Alimentos("Haba",3);
        alimentos[16] = new Alimentos("Lenteja",3);
        alimentos[17] = new Alimentos("Muslo de pollo",4);
        alimentos[18] = new Alimentos("Filete de Pescado",4);
        alimentos[19] = new Alimentos("Huevo",4);
        alimentos[20] = new Alimentos("Carne de Res",4);
        alimentos[21] = new Alimentos("Chuleta de cerdo",4);
    }


}
