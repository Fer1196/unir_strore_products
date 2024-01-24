package unir.store.products.exception;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.Data;
import lombok.EqualsAndHashCode;
import unir.store.products.utils.StoreConstants;

@Data
@EqualsAndHashCode(callSuper = true)
public class GenericException extends Exception {

        public Logger log = LogManager.getLogger(this.getClass());

        private static final long serialVersionUID = 1L;

        public Integer codeError;
        public String  statusError;
        public String  classError;
        public String  methodError;
        public Integer lineError;
        public String  userMessageError;
        public String  technicalMessageError;

        public GenericException(Class<?> classError, String methodError, String codeError, String statusError, String userMessageError) {
                super(userMessageError);
                setCodeError((codeError != null) ? Integer.valueOf(codeError) : StoreConstants.FAILURE_CODE_DEFAULT);
                setStatusError((statusError !=  null) ? statusError : String.valueOf(StoreConstants.FAILURE_STATUS_DEFAULT));
                setUserMessageError(userMessageError);
                setClassError(classError.getCanonicalName());
                setMethodError(methodError);
                setTechnicalMessageError(userMessageError);
                log.error("An Error ocurred in the class {}, in the method {} || UserErrorMessage: {}",
                                getClassError(), getMethodError(), getUserMessageError());
        }

        public GenericException(Class<?> classError, String methodError, String userMessageError, Throwable throwable) {
                super(throwable);
                List<StackTraceElement> errorList = new ArrayList<>();
                setClassError(classError.getCanonicalName());
                setUserMessageError(userMessageError);
                setCodeError((this.codeError != null) ? this.codeError : StoreConstants.FAILURE_CODE_DEFAULT);
                setStatusError((this.statusError != null) ? this.statusError : StoreConstants.FAILURE_STATUS_DEFAULT);
                for (StackTraceElement element : throwable.getStackTrace()) {
                        if (classError.getName().equalsIgnoreCase(element.getClassName())
                                        && element.getMethodName().equalsIgnoreCase(methodError)) {
                                errorList.add(element);
                        }
                }
                StackTraceElement element = (!errorList.isEmpty()) ? errorList.get(0) : null;
                setMethodError((element != null) ? element.getMethodName() : "ND");
                setLineError((element != null) ? element.getLineNumber() : 0);
                String message;

                if (throwable.getLocalizedMessage() != null) {
                        message = throwable.getLocalizedMessage();
                } else if (throwable.getCause() != null) {
                        message = throwable.getCause().toString();
                } else if (throwable.getMessage() != null) {
                        message = throwable.getMessage();
                } else {
                        message = throwable.toString();
                }

                setTechnicalMessageError(message);
            log.error("An Error ocurred in the class {}, in the method {} in the line {} || User error message: {} || Technical error: {}",
                                getClassError(), getMethodError(), getLineError(), userMessageError, getTechnicalMessageError());
                if (getMethodError().equalsIgnoreCase("ND")) {
                        log.error("chanfle");
                }
        }

        public GenericException(String userMessageError, String codeError ) {
                super(userMessageError);
                setCodeError((codeError != null) ? Integer.valueOf(codeError) : StoreConstants.FAILURE_CODE_DEFAULT);
                setUserMessageError(userMessageError);
                setStatusError((this.statusError != null) ? this.statusError : StoreConstants.FAILURE_STATUS_DEFAULT);
                log.error("An Error ocurred codeError {}, UserMessageError {} ",
                                getCodeError(), getUserMessageError());
        }
}