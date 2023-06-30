package tr.com.obss.jip2022.jip_backend.dto.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequest {

    String searchParam;

    Long bookId;
}
