package com.example.restservice;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CidadeController 
{
	private CidadeRepository cidadeRepository;
	
	//1. Ler o arquivo CSV das cidades para a base de dados;
	@GetMapping("/importar-cidades")
	public String importar() 
	{		
		List<Cidade> cidades = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("cidades.csv"))) 
		{
		    String line;
		    Cidade linha = new Cidade();
		    while ((line = br.readLine()) != null) {
		    	
		    	if(line.contains("ibge_id"))
		    	{
		    		continue;
		    	}
		    	
		        String[] cidade = line.split(",");
		        linha.setIbgeId(Integer.parseInt(cidade[0]));
		        linha.setUf(cidade[1]);
		        linha.setName(cidade[2]);
		        linha.setCapital(Boolean.parseBoolean(cidade[3]));
		        linha.setLon(Double.parseDouble(cidade[4]));
		        linha.setLat(Double.parseDouble(cidade[5]));
		        linha.setNoAccents(cidade[6]);
		        linha.setAlternativeNames(cidade[7]);
		        linha.setMicroRegion(cidade[8]);
		        linha.setMesoRegion(cidade[9]);	        
		        
		        cidades.add(linha);
		    }
		    
	        cidadeRepository.saveAll(cidades);
			return "Importação realizada com sucesso."; 
		    
		} catch (Exception e) 
		{
			return "Erro ao importar cidades do arquivo .csv. \n" + e.getMessage();
		}
	}
	
    //2. Retornar somente as cidades que são capitais ordenadas por nome;
    @GetMapping(path="/cidades-capitais")
    public @ResponseBody Iterable<Cidade> getAllCapitais() 
    { 
    	return cidadeRepository.getCapitais();
    }
    
    //4. Retornar a quantidade de cidades por estado;
    @GetMapping(path="/qtd-cidades-uf/")
    public @ResponseBody String getQtdeCidadesByUf(@RequestParam(required = true) String uf) 
    { 
    	return cidadeRepository.getQuantCidadesUf(uf).toString();
    }
    
    //5. Obter os dados da cidade informando o id do IBGE;
    @GetMapping(path="/cidade/")
    public @ResponseBody Cidade getCidadeByCodIbge(@RequestParam(required = true) int ibge_id) 
    { 
    	return cidadeRepository.findByCodigoIbge(ibge_id);
    }
    
    //6. Retornar o nome das cidades baseado em um estado selecionado;
    @GetMapping(path="/cidades-uf")
    public @ResponseBody String getAllCidadesByUf(@RequestParam(required = true) String uf) 
    {
      return cidadeRepository.getAllCidadesByUf(uf);
    }
    
    //7. Permitir adicionar uma nova Cidade;
    @GetMapping(path="/cidade/add")
    public @ResponseBody Cidade getAllCidadesByUf(@RequestParam(required = true) Cidade cidade) 
    {
      return cidadeRepository.save(cidade);
    }
    
    //8. Permitir deletar uma cidade;
    @GetMapping(path="/cidade/delete")
    public @ResponseBody String getAllCidadesByUf(@RequestParam(required = true) int ibge_id) 
    {
      cidadeRepository.deleteById(ibge_id);
      if(cidadeRepository.findByCodigoIbge(ibge_id) != null)
      {
    	  return "Erro ao excluir.";
      }
      else
      {
    	  return "Excluído com sucesso.";
      }
    }
    
    //10. Retornar a quantidade de registro baseado em uma coluna. Não deve contar itens iguais;
    @GetMapping(path="/qtd-itens-coluna")
    public @ResponseBody String getTotalItensColuna(@RequestParam(required = true) String coluna) 
    {
    	return cidadeRepository.getQuantItensPorColuna(coluna).toString();
    }

    //11. Retornar a quantidade de registros total;
    @GetMapping(path="/qtd-cidades")
    public @ResponseBody String getQtdCidades() 
    {
      return cidadeRepository.getQuantCidades().toString();
    }
    
    //12. Dentre todas as cidades, obter as duas cidades mais distantes uma da outra com base na localização (distância em KM em linha reta);
    @GetMapping(path="/cidades-mais-distantes")
    public @ResponseBody String getDistancia() 
    {
      ArrayList<Cidade> cidades1 = cidadeRepository.getAllCidades();
      ArrayList<Cidade> cidades2 = cidadeRepository.getAllCidades();
      double distancia = 0;
      Cidade cidade1 = null;
      Cidade cidade2 = null;
      
      
      for (int i = 0; i < cidades1.size(); i++ ) {  
  	    	
  	    	for (int j = 0; j < cidades2.size(); j++) {  
  	    	    	
  	    	    	double latitude = (cidades1.get(i).getLat() - cidades2.get(j).getLat()) * (cidades1.get(i).getLat() - cidades2.get(j).getLat());
  	    	    	double longitude = (cidades1.get(i).getLon() - cidades2.get(j).getLon()) * (cidades1.get(i).getLon() - cidades2.get(j).getLon());
  	    	    	double distanciaNova = Math.sqrt(latitude + longitude);
  	    	    	
  	    	    	if(distanciaNova > distancia)
  	    	    	{
  	    	    		distancia = distanciaNova;
  	    	    		cidade1 = cidades1.get(i);
  	    	    		cidade2 = cidades2.get(j);
  	    	    	}  	    	    	
  	    	  }  	    	
  	  }
      
      return "Cidades mais distantes: " + cidade1.getName() + " e " + cidade2.getName() + ". Distância: " + distancia + " KM.";
    }
    
}













