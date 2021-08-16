package io.speedy.poc.core.usecase.transaction.transferobject.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParentResponse {
    private TransactionSubClass transaction;
}
