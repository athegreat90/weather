package com.training.weatherservice.dto;

public class ResponseGenericDto<T>
{
    private Integer code;
    private String message;
    private T body;

    public ResponseGenericDto(Integer code, String message, T body)
    {
        this.code = code;
        this.message = message;
        this.body = body;
    }

    /**
     * Gets code
     *
     * @return value of code
     */
    public Integer getCode()
    {
        return code;
    }

    /**
     * Set code java.lang.Integer
     */
    public void setCode(Integer code)
    {
        this.code = code;
    }

    /**
     * Gets message
     *
     * @return value of message
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * Set message java.lang.String
     */
    public void setMessage(String message)
    {
        this.message = message;
    }

    /**
     * Gets body
     *
     * @return value of body
     */
    public T getBody()
    {
        return body;
    }

    /**
     * Set body T
     */
    public void setBody(T body)
    {
        this.body = body;
    }

    /**
     * @return Dto format to string
     */
    @Override
    public String toString()
    {
        return String.format("ResponseGenericDto{code=%d, message='%s', body=%s}", code, message, body);
    }
}
