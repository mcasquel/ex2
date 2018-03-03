import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Pais {

	private int id;
	private String nome;
	private long populacao;
	private double area;
	
	//MysqlConnection
	static {
		try {
		Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Pais() {
		
	}

	public Pais(int id,String nome, long populacao, double area) {
		super();
		this.id = id;
		this.nome = nome;
		this.populacao = populacao;
		this.area = area;
	}
	
	//Obter Conexao com o banco
	public Connection obtemConexao() throws SQLException {
		return DriverManager
		.getConnection("jdbc:mysql://localhost/Pais?user=root&password=@mcasquel123");
		}
	
	//Cria pais no banco
	public void criar() {
		String sqlInsert = "INSERT INTO paises(nome,populacao,area) VALUES (?, ?, ?)";
		
		try (Connection conn = obtemConexao();
		PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
		stm.setString(1, getNome());
		stm.setLong(2, getPopulacao());
		stm.setDouble(3, getArea());
		stm.execute();
		
		String sqlQuery = "SELECT LAST_INSERT_ID()";
		try(PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
				ResultSet rs = stm2.executeQuery();) {
				if(rs.next()){
					setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				System.out.println("TEste");
				e.printStackTrace();
			}
		} catch (SQLException e) {
			System.out.println("TEste2");
			e.printStackTrace();
			}
		}
		
	//Realizar Update no BD
	public void atualizar() {
		//aqui e necessario o ID pois para realizar o update precisa referenciar para algo
		String sqlUpdate = "UPDATE paises SET nome=?, populacao=?, area=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			
			stm.setString(1, getNome());
			stm.setLong(2, getPopulacao());
			stm.setDouble(3, getArea());
			stm.setInt(4, getId());
			stm.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
			}
		}
	
	//Realizar exclusao de dado no BD
	public void excluir() {
		String sqlDelete = "DELETE FROM paises WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			
			stm.setInt(1, getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
			}
		}
	
	//Mostrar dados importados do banco que foi criado
	public void carregar() {
		String sqlSelect = "SELECT nome, populacao, area FROM paises WHERE paises.id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				stm.setInt(1, getId());
			try (ResultSet rs = stm.executeQuery();) {
				//se existir retorno do result set, para isso usa o next()
				if (rs.next()) {
					setNome(rs.getString("nome"));
					setPopulacao(rs.getLong("populacao"));
					setArea(rs.getDouble("area"));
				} else {
					setId(-1);
					setNome(null);
					setPopulacao(0);
					setArea(0);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
			}
		}
	
	
	public void paisHabitantes() {
		
		String sqlGet = "Select * from paises where populacao = (Select Max(populacao) from paises)";
		
		try (Connection conn = obtemConexao()){
			PreparedStatement stm = conn.prepareStatement(sqlGet);
			
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				//enviando para os metodos set o valor coletado da query
				setNome(rs.getString("nome"));
				setPopulacao(rs.getLong("populacao"));
				setId(rs.getInt("id"));
				setArea(rs.getDouble("area"));
			}else {
				System.out.println("Error Result Set");
			}
			
		}catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void paisMenorArea() {
		
		String sqlGet = "Select * from paises where area = (Select Min(area) from paises)";
		
		try (Connection conn = obtemConexao()){
			PreparedStatement stm = conn.prepareStatement(sqlGet);
			
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				//enviando para os metodos set o valor coletado da query
				setNome(rs.getString("nome"));
				setPopulacao(rs.getLong("populacao"));
				setId(rs.getInt("id"));
				setArea(rs.getDouble("area"));
			}else {
				System.out.println("Error Result Set");
			}
			
		}catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	
	public String[] paisVetPaises() {
		
		String sqlGet = "Select nome from paises order by nome";
		String[] array = new String[3];
		int cont = 0 ;
		
		try (Connection conn = obtemConexao()){
			PreparedStatement stm = conn.prepareStatement(sqlGet);
			
			ResultSet rs = stm.executeQuery();
			
			while(rs.next() && cont < 3 ) {
				//Adicionando itens no arraylist
				array[cont] = rs.getString("nome");
				cont ++;
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
		
		return array;
	}
	
	
	public String toString() {
		
		return "ID: " + getId() + " \nNome: "+getNome()+" \nPop: "+getPopulacao()+" \nArea: "+getArea();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getPopulacao() {
		return populacao;
	}

	public void setPopulacao(long populacao) {
		this.populacao = populacao;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}
	
	
}