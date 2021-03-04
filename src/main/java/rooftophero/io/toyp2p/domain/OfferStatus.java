package rooftophero.io.toyp2p.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OfferStatus {
    OPEN("모집중"),
    CLOSE("모집완료"),
    CANCEL("모집취소"),
    PAY("상환중"),
    FINISH("상환완료"),
    PENDING("상환지연"),
    OVERDUE("연체"),
    DEFAULT("부도");

    private String contents;
}
