package validation;

public abstract class Validator<T> {
    private String ValidationFeedback;

    public abstract boolean isValid(T entity);

    public String getValidationFeedback() {
        return ValidationFeedback;
    }

    protected boolean validateString(String str, int minLength, int maxLength,String regex){
        return str !=null && str.length() <= maxLength && str.length() >=minLength &&
                str.matches(regex);
    }

    public void setValidationFeedback(String validationFeedback) {
        ValidationFeedback = validationFeedback;
    }
}
