package io.speedy.poc.core.ports.out.transaction.transferobject.pageresponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PageResponse {
    private int per_page;
    private int current_page;
    private String next_page_url;
    private Object prev_page_url;
    private int from;
    private int to;
    private List<Datum> data;
}


