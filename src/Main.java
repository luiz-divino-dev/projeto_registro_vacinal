import org.postgresql.jdbc.PgResultSet;

import java.sql.Array;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Conect con = new Conect();
        String[][] paciente = new String[2][8];
        paciente[0][0] = "nome";
        paciente[0][1] = "CPF";
        paciente[0][2] = "telefone";
        paciente[0][3] = "cep";
        paciente[0][4] = "cidade";
        paciente[0][5] = "uf";
        paciente[0][6] = "escolaridade";
        paciente[0][7] = "idade";

        String[][] vacina = new String[2][2];
        vacina[0][0] = "nome";
        vacina[0][1] = "validade";

        paciente[1][0] = "João2";
        paciente[1][1] = "4194757106";
        paciente[1][2] = "96846741";
        paciente[1][3] = "74330010";
        paciente[1][4] = "goiania";
        paciente[1][5] = "GO";
        paciente[1][6] = "superior";
        paciente[1][7] = "20";

        vacina[1][0] = "ebola";
        vacina[1][1] = "2022-10-20";


//        String sql_insert = insert_sql_paciente(paciente);
//        String sql_insert_vacina = insert_sql_vacina(vacina);
//        con.executeSql(sql_insert);
//        con.executeSql(sql_insert_vacina);
//        select_all_paciente(paciente, con);
//        obter_vacinas_paciente(paciente[1][1], con);
//        select_stats_escolaridade(con);
        select_all_vacinas(vacina, con);
    }

    public static String insert_sql_paciente(String[][] paciente) {
        return String.format("INSERT INTO Paciente (%s, %s, %s, %s, %s, %s, %s, %s) VALUES" +
                        "('%s', '%s', %d, '%s', '%s', '%s', '%s', %d);"
                , paciente[0][0], paciente[0][1], paciente[0][2], paciente[0][3], paciente[0][4]
                , paciente[0][5], paciente[0][6], paciente[0][7], paciente[1][0], (paciente[1][1]),
                Integer.parseInt(paciente[1][2]), paciente[1][3], paciente[1][4], paciente[1][5], paciente[1][6],
                Integer.parseInt(paciente[1][7]));
    }

    public static String insert_sql_vacina(String[][] vacina) {
        return String.format("INSERT INTO Vacinas (%s, %s) VALUES ('%s', '%s');",
                vacina[0][0], vacina[0][1], vacina[1][0], vacina[1][1]);
    }

    public static String insert_sql_vacina_paciente(int id_vacina, String cpf_paciente) {
        return String.format("insert into Vacinas_x_Paciente (id_vacina, cpf_paciente) values (%d,'%s');\n", id_vacina, cpf_paciente);

    }


    public static void obter_vacinas_paciente(String id_paciente, Conect con) {
        String sql = String.format("SELECT VC.nome FROM Vacinas_x_Paciente AS VP INNER JOIN Paciente AS PC ON VP.cpf_paciente = PC.CPF INNER JOIN vacinas AS VC ON VP.id_vacina = VC.ID_Vacina WHERE PC.CPF = '%s';", id_paciente);
        ResultSet resultado = con.executeQuery(sql);


        try {
            System.out.println("Vacinas do paciente: " );
            while (resultado.next()) {
                String nome = resultado.getString("nome" );
                System.out.println("- " + nome);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList select_all_vacinas(String[][] vacina, Conect con) {
        String select_sql = "select * from Vacinas;";
        ResultSet resultado = con.executeQuery(select_sql);
        ArrayList<String[]> vacinas = new ArrayList<String[]>();
        try {
            while (resultado.next()) {
                String[] vacina_row = new String[2];
                vacina_row[0] = resultado.getString("nome" );
                vacina_row[1] = Integer.toString(resultado.getInt("ID_vacina" ));
                vacinas.add(vacina_row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vacinas;
    }

//    public static String drop_table(String table_name) {
//        return String.format("drop table %s", table_name);
//    }

    public static void select_all_paciente(String[][] paciente, Conect con) {
        String select_sql = "select * from Paciente;";
        ResultSet resultado = con.executeQuery(select_sql);
        ArrayList<String[]> pacientes = new ArrayList<String[]>();
        try {
            while (resultado.next()) {
                System.out.println("-----------------------------------------------------" );
                String nome = resultado.getString(paciente[0][0]);
                String cpf = resultado.getString(paciente[0][1]);
                int telefone = resultado.getInt(paciente[0][2]);
                String cep = resultado.getString(paciente[0][3]);
                String cidade = resultado.getString(paciente[0][4]);
                String uf = resultado.getString(paciente[0][5]);
                String escolaridade = resultado.getString(paciente[0][6]);
                int idade = resultado.getInt(paciente[0][7]);


//                System.out.println("nome" + "-  " + nome);
//                System.out.println("CPF" + "-  " + cpf);
//                System.out.println("telefone" + "-  " + telefone);
//                System.out.println("cep" + "-  " + cep);
//                System.out.println("cidade" + "-  " + cidade);
//                System.out.println("uf" + "-  " + uf);
//                System.out.println("escolaridade" + "-  " + escolaridade);
//                System.out.println("idade" + "-  " + idade);
//                System.out.println("-----------------------------------------------------" );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void select_stats_escolaridade(Conect con) {
        String sql = "SELECT escolaridade, COUNT (*) AS quant_escolaridade  FROM Paciente GROUP BY escolaridade;";
        ResultSet resultado = con.executeQuery(sql);
        try {
            while (resultado.next()) {
                String escolaridade = resultado.getString("escolaridade" );
                int quant_escolaridade = resultado.getInt("quant_escolaridade" );
                System.out.println(escolaridade + "-  " + quant_escolaridade);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//        AC NORTE
//        PA NORTE
//        RO NORTE
//        RR NORTE
//        TO NORTE
//        MA NORDESTE
//        PB NORDESTE
//        PE NORDESTE
//        PI NORDESTE
//        RN NORDESTE
//        SE NORDESTE
//        GO CENTRO-OESTE
//        MS CENTRO-OESTE
//        MT CENTRO-OESTE
//        ES SUDESTE
//        MG SUDESTE
//        RJ SUDESTE
//        SP SUDESTE
//        RS SUL
//        SC SUL
//        AM NORTE
//        AP NORTE
//        AL NORDESTE
//        BA NORDESTE
//        CE NORDESTE
//        PR SUL





