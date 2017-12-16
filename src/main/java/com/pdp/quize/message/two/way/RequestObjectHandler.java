package com.pdp.quize.message.two.way;

import java.io.Serializable;

public interface RequestObjectHandler<Req extends Serializable, Res extends Serializable> {

    Res handle(Req reqObject);

}
