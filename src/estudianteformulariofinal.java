import javax.swing.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class estudianteformulariofinal {
    private JPanel getEstudiante;
    private JTextField cedula_estudiante;
    private JTextField nombre_estudiante;
    private JTextField apellido_estudiante;
    private JButton cargasDatosDesdeElButton;
    private JButton guardarDatosEnDiscoButton;
    private JButton verSiguienteRegistroButton;
    private JButton verAnteriorRegistroButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JCheckBox rojoCheckBox;
    private JCheckBox verdeCheckBox;
    private JCheckBox ningunoCheckBox;
    private JCheckBox siCheckBox;
    private JCheckBox noCheckBox;
    private JLabel Codigo;
    private JLabel Cedula;
    private JLabel Nombre;
    private JLabel Apellido;
    private JLabel Signo;
    private JLabel Fecha_nacimiento;
    private JLabel Color_favorito;
    private JPanel Estudiante;
    private JTextField codigo_estudiante;

    private ArrayList<RegistroEstudiante> registros;
    private int indiceActual;

    public estudianteformulariofinal() {
        registros = new ArrayList<>();
        indiceActual = -1;

        cargasDatosDesdeElButton.addActionListener(this::cargarDatosDesdeElButtonActionPerformed);
        guardarDatosEnDiscoButton.addActionListener(this::guardarDatosEnDiscoButtonActionPerformed);
        verSiguienteRegistroButton.addActionListener(this::verSiguienteRegistroButtonActionPerformed);
        verAnteriorRegistroButton.addActionListener(this::verAnteriorRegistroButtonActionPerformed);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("estudianteformulariofinal");
        frame.setContentPane(new estudianteformulariofinal().Estudiante);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void cargarDatosDesdeElButtonActionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showOpenDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                registros = (ArrayList<RegistroEstudiante>) ois.readObject();
                indiceActual = 0;

                mostrarRegistro(indiceActual);

                JOptionPane.showMessageDialog(null, "Datos cargados exitosamente desde el archivo");
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void guardarDatosEnDiscoButtonActionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showSaveDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
                oos.writeObject(registros);
                JOptionPane.showMessageDialog(null, "Datos guardados exitosamente en el archivo");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void verSiguienteRegistroButtonActionPerformed(ActionEvent e) {
        if (indiceActual < registros.size() - 1) {
            indiceActual++;
            mostrarRegistro(indiceActual);
        }
    }

    private void verAnteriorRegistroButtonActionPerformed(ActionEvent e) {
        if (indiceActual > 0) {
            indiceActual--;
            mostrarRegistro(indiceActual);
        }
    }

    private void mostrarRegistro(int indice) {
        RegistroEstudiante registro = registros.get(indice);

        cedula_estudiante.setText(registro.getCedula());
        nombre_estudiante.setText(registro.getNombre());
        apellido_estudiante.setText(registro.getApellido());
        // Asignar valores a otros campos según sea necesario
    }

    private static class RegistroEstudiante implements Serializable {
        private String cedula;
        private String nombre;
        private String apellido;

        public String getCedula() {
            return cedula;
        }

        public void setCedula(String cedula) {
            this.cedula = cedula;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public void setApellido(String apellido) {
            this.apellido = apellido;
        }
    }
}