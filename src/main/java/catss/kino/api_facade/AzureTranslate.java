package catss.kino.api_facade;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
class TextBody {
    private String text;
    public TextBody(String text) {
        this.text = text;
    }
}

@Getter
@Setter
@NoArgsConstructor
class TranslationResponse {
    private List<TranslationItem> translations;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class TranslationItem {
        private String text;
        private String to;
    }
}

@Service
public class AzureTranslate {

    RestTemplate restTemplate;

    public AzureTranslate() {
        restTemplate = new RestTemplate();
    }

    // Sets up stuff needed for translation
    @Value("${app.azure-translate-key}")
    String SUBSCRIPTION_KEY;
    String REGION = "northeurope";

    String AZURE_TRANSLATE_URL = "https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&from=en&to=da";

    public String translate(String txt) {

        // Set up the headers needed for our request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Ocp-Apim-Subscription-Key", SUBSCRIPTION_KEY);
        headers.set("Ocp-Apim-Subscription-Region", REGION);

        // Set up body for request
        TextBody t = new TextBody(txt);
        List<TextBody> body = Collections.singletonList(t);

        //Collect to an HttpEntity
        HttpEntity<List<TextBody>> entity = new HttpEntity<>(body, headers);
        // POST request to translate the text
        ResponseEntity<TranslationResponse[]> response = restTemplate.exchange(AZURE_TRANSLATE_URL, HttpMethod.POST, entity, TranslationResponse[].class);
        System.out.println(response.getBody());

        return response.getBody()[0].getTranslations().get(0).getText();
    }

    public static void main(String[] args) {
        String result = new AzureTranslate().translate("hello World");
        System.out.println(result);
    }
}