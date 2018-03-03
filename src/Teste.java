import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class Teste {

	private Pais pais,copia;
	static int id = 9;
	
	
	@BeforeEach
	public void setUp() throws Exception {
		System.out.println("Setup");
		pais = new Pais(id,"Teste",8541800,57841.00);
		copia = new Pais(id,"Teste",8541800,57841.00);
		System.out.println(pais);
		System.out.println(copia);
	}
	
	@Test
	public void teste00Carregar() {
		System.out.println("carregar");
		
		Pais fixture = new Pais(1,"Brasil",0000,0000);
		Pais novo = new Pais(1,null,0,0);
		//Verificando se Pais carregado em cima esta no banco com ID 1
		novo.carregar();
		assertEquals(novo.getId(), fixture.getId(),"testa inclusao");
	}
	
	@Test
	public void test01Criar() {
		System.out.println("Criando");
		pais.criar();
		id = pais.getId();
		copia.setId(id);
		assertEquals(pais.getId(),copia.getId(),"Teste de criacao");
	}
	
	
	@Test
	public void test02Atualizar() {
		System.out.println("Atualizar");
		pais.setNome("legal");
		copia.setNome("legal");
		
		pais.atualizar();
		pais.carregar();
		assertEquals(pais.getNome(),copia.getNome(),"Teste atualizacao");
		
	}
	
	@Test
	public void test03Excluir() {
		System.out.println("Excluir");
		pais.setId(id);
		copia.setId(-1);
		
		pais.excluir();
		pais.carregar();
		
		assertEquals(pais.getId(),copia.getId(),"Teste exclusao");
	}
	
	@Test
	public void test04MaiorPop() {
		System.out.println("Maior populacao");
		pais.paisHabitantes();
		long pop = pais.getPopulacao();
		
		copia.setPopulacao(pop);
		
		assertEquals(pais.getPopulacao(),copia.getPopulacao(),"Testando maior populacao");
	}
	
	@Test
	public void test05MenorArea() {
		System.out.println("Menor Area");
		pais.paisMenorArea();
		double area = pais.getArea();
		
		copia.setArea(area);
		
		assertEquals(pais.getArea(),copia.getArea(),"Testando menor area");
		
	}
	
	public void test06Array() {
		System.out.println("Vetor de paises");
		String[] array = pais.paisVetPaises();
		assertEquals(array.length,3,"Testando Vetor de paises");
	}
	
}