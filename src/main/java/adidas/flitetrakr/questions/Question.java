package adidas.flitetrakr.questions;

import java.util.Optional;

public interface Question<T, S> {
    Optional<T> answer(S input);
}
