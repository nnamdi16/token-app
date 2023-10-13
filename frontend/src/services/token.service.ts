import { client } from "../api/httprequests"
import { urls } from "../api/urls"
import { createTokenUrl, validateTokenUrl } from "../Config"

interface ResponseProps {
    success: boolean,
    data: string,
    error: string,
    message: string,
    status: number
}
export const generateToken = async (token: string) => {
    const response = await client(createTokenUrl).post(`${urls.create}`, {
        pin: token
    }).then(response => {
        return response?.data as ResponseProps
    }).catch(err => {
        return err?.response?.data
    })

    return response;
}

export const validateToken = async (token: string) => {
    const response = await client(validateTokenUrl).get(`${urls.validate}/${token}`).then(response => {
        return response?.data as ResponseProps
    }).catch(err => {
        return err?.response?.data
    })

    return response;
}