package io.speedy.poc.core.ports.out.report.transferobject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    private String status;
    private List<ReportItems> reponse;
}
