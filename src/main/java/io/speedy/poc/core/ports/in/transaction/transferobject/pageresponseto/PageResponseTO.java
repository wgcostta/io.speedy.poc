package io.speedy.poc.core.ports.in.transaction.transferobject.pageresponseto;

import io.speedy.poc.core.usecase.transaction.transferobject.pageresponse.PageResponse;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PageResponseTO {
    private int per_page;
    private int current_page;
    private String next_page_url;
    private Object prev_page_url;
    private int from;
    private int to;
    private List<DatumTO> data;

    public static PageResponseTO from(PageResponse pageResponse) {
        return  PageResponseTO.builder()
                        .per_page(pageResponse.getPer_page())
                        .current_page(pageResponse.getCurrent_page())
                        .next_page_url(pageResponse.getNext_page_url())
                        .prev_page_url(pageResponse.getPrev_page_url())
                        .from(pageResponse.getFrom())
                        .to(pageResponse.getTo())
                        .data(DatumTO.from(pageResponse.getData()))
                        .build();

    }
}


