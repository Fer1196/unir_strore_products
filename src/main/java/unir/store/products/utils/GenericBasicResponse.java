package unir.store.products.utils;

import lombok.Data;

@Data
public class GenericBasicResponse<T> {

        public GenericBasicResponse() {
        }

        private int code;
        private String status = StoreConstants.STATUS_DEFAULT;
        private T data;

}
