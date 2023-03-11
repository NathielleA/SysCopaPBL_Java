package app.DadosArquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.model.*;

public class DadosPreCadastro {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("MyyyyHHmmssSSS");

	/**
	 * 
	 * @param SelecaoDAO
	 * @param JogadorDAO
	 * @param GruposCRUD
	 */
	public static void LeituraArquivos(SelecaoDAOImpl SelecaoDAO, JogadorDAOImpl JogadorDAO, FaseGrupos GruposCRUD) {
		
		try {
			String arquivoPath = new File("src/app/DadosArquivo/arquivoDeDados.txt").getAbsolutePath();

			FileInputStream arquivo = new FileInputStream(arquivoPath);
			InputStreamReader input = new InputStreamReader(arquivo);
			BufferedReader buffer = new BufferedReader(input);
			
			String linha = buffer.readLine();	
			
			while (linha != null) {
				
				//Lendo o arquivo das seleçoes
				String[] selecaoGrup = linha.split(";");
				
				List<Jogador> listaJogadores = new ArrayList<>();
				
				Selecao novaSelecao = new Selecao(selecaoGrup[0], listaJogadores, selecaoGrup[1]);
				
				SelecaoDAO.inserir(novaSelecao);
				GruposCRUD.atualizaGrupos(selecaoGrup[1], novaSelecao);
				
				linha = buffer.readLine();	
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao ler arquivo. Erro: " + e.getMessage());
		}
		
		//Leitura Jogadores
		try {
			String arquivoJogPath = new File("src/app/DadosArquivo/dadosJogadores.txt").getAbsolutePath();
			
			FileInputStream arquivoJog = new FileInputStream(arquivoJogPath);
			InputStreamReader input = new InputStreamReader(arquivoJog);
			BufferedReader buffer = new BufferedReader(input);
			
			String linha = "-";
			
			while (linha != null) {
				linha = buffer.readLine();//leitura da divisória
				
				linha = buffer.readLine();//leitura do nome da Seleção
				String selecao = linha;
				
				if (linha == null) {break;}
				
				for (int i=0; i < 11; i++) {
					linha = buffer.readLine();//leitura do nome do Jogador e sua Posição

					String[] jogadoresPos = linha.split(";");
					
					Selecao selecaoJog = SelecaoDAO.verificaSelecao(selecao);
					
					Thread.sleep(1); //intervalo para não gerar códigos iguais
					Date data = new Date();
					
					Jogador novoJog = new Jogador(sdf.format(data), jogadoresPos[0], jogadoresPos[1], selecaoJog);

					ArrayList<PartidaJogador> listaJogPart = new ArrayList<>();
					novoJog.setPartidasJogador(listaJogPart);

					// ADICIONANDO O JOGADOR NOVO NA LISTA DE SEU DAO E DA SUA SELEÇAO
					JogadorDAO.inserir(novoJog);
					selecaoJog.setJogadores(novoJog);
				}
			}
			
		} catch(Exception e) {
			System.out.println("Erro ao ler arquivo de Jogadores. Erro: " + e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param SelecaoDAO
	 * @param JogadorDAO
	 * @param GruposCRUD
	 */
	public static void LeituraTecnicoArbitro(SelecaoDAOImpl SelecaoDAO, TecnicoDAOImpl TecnicoDAO, ArbitroDAOImpl ArbitroDAO, FaseGrupos GruposCRUD) {
		//LENDO TÉCNICOS
		try {
			String arquivoPath = new File("src/app/DadosArquivo/dadosTecnicos.txt").getAbsolutePath();

			FileInputStream arquivoTec = new FileInputStream(arquivoPath);
			InputStreamReader input = new InputStreamReader(arquivoTec);
			BufferedReader buffer = new BufferedReader(input);
			
			String linha = buffer.readLine();	
			
			while (linha != null) {
				
				//separando nome da seleção e do técnico
				String[] selTecnico = linha.split(";");
				
				Selecao selecao = SelecaoDAOImpl.verificaSelecao(selTecnico[0]);
				
				Tecnico novoTecnico = new Tecnico(selTecnico[1]);
				selecao.setTecnico(novoTecnico);
				novoTecnico.setSelecao(selecao);

				// ADICIONAR O TECNICO NA LISTA DO DAO
				TecnicoDAO.inserir(novoTecnico);
		
				linha = buffer.readLine();	
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao ler arquivo. Erro: " + e.getMessage());
		}
		
		//LENDO ÁRBITROS
		try {
			String arquivoPath = new File("src/app/DadosArquivo/dadosArbitros.txt").getAbsolutePath();

			FileInputStream arquivoArb = new FileInputStream(arquivoPath);
			InputStreamReader input = new InputStreamReader(arquivoArb);
			BufferedReader buffer = new BufferedReader(input);
			
			String linha = buffer.readLine();	
			
			while (linha != null) {
				
				// CRIANDO O ÁRBITRO E ADICIONANDO NA LISTA DO DAO
				Arbitro novoArbitro = new Arbitro(linha);
				ArbitroDAO.inserir(novoArbitro);
		
				linha = buffer.readLine();	
			}
			
		} catch (Exception e) {
			System.out.println("Erro ao ler arquivo. Erro: " + e.getMessage());
		}
	}
	
}
