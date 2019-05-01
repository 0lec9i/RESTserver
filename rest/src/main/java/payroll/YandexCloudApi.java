package payroll;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@AllArgsConstructor
public class YandexCloudApi {

    private static final String folderId = "b1gltmq9g8uepiiouluu";
    private static final String iamToken = "CggaATEVAgAAABKABBSGojNKni5WJ-VQHQNkW-124u68taMNNA00TPVFXncmoWSnQNt_n-gnuTsB1AqRmKidq-NAGzo0rP-FduoxlgTO1XAgp4PvPrr042BjHGBZHWnzVN3e_uQJWQLdaGEaAZjy97svLl9znbTvhw_MMrnNriUBucinkDMvd8-STHa1Efild_oXR3sJ6Lr5kFQYJpqxgW-g5ilH8d2J9fLD5RT0PIAidvy1ZN4p59fcXLvhK1FU6A9_UrPdF4BFOTbcqZ8X8sl514o5lm8beHyYEELxEACuGuLivLQsaq5TS8DbrKuBjPUpVriSH4Z1_8cRT2Cs9kB1ENJLd1Jw7Cmo4Dg9k-Aqa4fendQuTDPtpgNPS2GO4Y581aW4EivAJT4i-pTG2aPgLIInLsAASqz_Ojj5Dzv5DpLgT9jzO2JOeqxRXgM1FaLmlsWnoym7PLfoNw6EWiLzFVfFdeqXQaYqKYS6aWEi1YPRsHtOLzWx04ByBRT_KJ-tOJ_bJ2-2WS_3fSx2BBmSmxVEG0GSuEyVahg0HrElVDw6SGdodrROMUoKoWsONbc5yPo_66fD9rFu9IGIT_DxcDwhnbdMTeURCXcL0s56SbwF9h6LIoLd0D24nCSHBCVdsODd0v7E-mdrfmk5ZxjfN7BqRbKmuVX2D24L5wk7FjUNAb30RmkJg9TBGmcKIDU0MmMzYWM1OWFhMzQyYjg5MzEwMGM0NjE3MTExMmEzEKTQpuYFGOShqeYFIiUKFGFqZWdtc2U4dHFuaGdkaXZhcWZqEg1hZHJpYW5vLndpc2gyWgAwAjgBSggaATEVAgAAAFABIPYE";

    private RestTemplate restTemplate;

    public RecognizeResponse recognize(byte[] audioOgg) {
        String url = UriComponentsBuilder.fromUriString("https://stt.api.cloud.yandex.net/speech/v1/stt:recognize")
                .queryParam("topic", "general")
                .queryParam("folderId", folderId).toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + iamToken);
        headers.set("Transfer-Encoding", "chunked");

        HttpEntity<byte[]> entity = new HttpEntity<>(audioOgg, headers);
        ResponseEntity<RecognizeResponse> response = restTemplate.postForEntity(url, entity, RecognizeResponse.class);
        log.info(response.toString());
        return response.getBody();
    }
}
