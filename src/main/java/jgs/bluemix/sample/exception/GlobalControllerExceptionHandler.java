package jgs.bluemix.sample.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * 全てのControllerに跨がるExceltptionHandlerクラスです.
 *
 * @author ryozo
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @Autowired
    MessageSource messageSource;

    @ExceptionHandler(SystemException.class)
    public ModelAndView handleSystemException(HttpServletRequest request, SystemException exception, Locale locale) {
        ModelAndView mav = new ModelAndView("error/error");
        mav.addObject("url", request.getRequestURL());
        if (hasErrorMessage(exception)) {
            String message = messageSource.getMessage(exception.getErrorCode().getCode(), null, locale);
            mav.addObject("message", message);
        }
        mav.addObject("exception", exception);
        return mav;
    }

    private boolean hasErrorMessage(SystemException e) {
        return e.getErrorCode() != null;
    }
}
