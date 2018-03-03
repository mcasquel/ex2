public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Pais brasil = new Pais();

		brasil.setNome("teste");
		brasil.setArea(454545.00);
		brasil.setPopulacao(654654546);
		
		brasil.criar();
		
		String [] array = brasil.paisVetPaises();
		
		for(int i = 0; i <array.length;i++) {
			System.out.println(array[i]);
		}

	}

}