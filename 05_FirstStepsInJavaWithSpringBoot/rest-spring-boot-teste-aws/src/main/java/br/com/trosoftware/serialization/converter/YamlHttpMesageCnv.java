package br.com.trosoftware.serialization.converter;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

/*Nome da Classe fou alterado pois o tamanho estava dando conflito no versionamento do github
 * foi feita a seguinte alteração: YamlJackson2HttpMesageConverter - > YamlHttpMesageCnv
 * */
public class YamlHttpMesageCnv extends AbstractJackson2HttpMessageConverter{

	public YamlHttpMesageCnv() {
		super(new YAMLMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL),
				MediaType.parseMediaType("application/x-yaml"));
	}

}
