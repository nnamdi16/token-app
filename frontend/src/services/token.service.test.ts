import axios, { AxiosInstance } from 'axios';
import MockAdapter from 'axios-mock-adapter';
import { createTokenUrl, validateTokenUrl } from '../Config';
import { urls } from '../api/urls';
import { client } from '../api/httprequests';
import { generateToken, validateToken } from './token.service';


describe('Token service', () => {

    beforeEach(() => {
        jest.clearAllMocks()
    })
    describe('Generate Token method', () => {
        let mock: MockAdapter;
        let axiosInstance: AxiosInstance;

        beforeEach(async () => {
            mock = new MockAdapter(axios);
            axiosInstance = client(createTokenUrl)
        });
        const mockedResponse = {
            success: true,
            data: "2290-9009-2990-9099",
            error: null,
            message: "successfully generated token",
            status: 200
        }

        const errorResponse = {
            success: false,
            data: null,
            error: "Pin must be 5 digits",
            message: "Pin must be 5 digits",
            status: 400
        }
        it('axios client should return the right response', async () => {
            mock.onPost(urls.create, { pin: '29099' }).reply(200, mockedResponse);
            const result = await axiosInstance.post(urls.create, { pin: '29099' });
            expect(result.data).toEqual(mockedResponse)
        });

        it('Generate token should return a token', async () => {
            jest.spyOn(axiosInstance, "post").mockImplementation(() => Promise.resolve(mockedResponse))
            mock.onPost(urls.create, { pin: '29099' }).reply(200, mockedResponse);
            const result = await generateToken('29099');
            expect(result.data).toEqual(mockedResponse.data)
        });

        it('Generate token should return error when the input is wrong', async () => {
            mock.onPost(urls.create, { pin: '290997447' }).reply(400, errorResponse);
            jest.spyOn(axiosInstance, "post").mockImplementation(() => Promise.reject(errorResponse))

            const result = await generateToken('290997447');
            expect(result.data).toEqual(errorResponse.data)
        });

    })

    describe('Validate Token method', () => {
        let mock: MockAdapter;
        let axiosInstance: AxiosInstance;

        beforeEach(async () => {
            mock = new MockAdapter(axios);
            axiosInstance = client(validateTokenUrl)
        });
        
        const mockedResponse = {
            success: true,
            data: true,
            error: null,
            message: "successfully validated token",
            status: 200
        }
     
        it('Generate token client should return a token response', async () => {
            mock.onGet(`${urls.validate}/2290-9009-2990-9099`).reply(200, mockedResponse);
            const result = await axiosInstance.get(`${urls.validate}/2290-9009-2990-9099`);
            expect(result.data).toEqual(mockedResponse)
        });

        it('Generate token should return a token', async () => {

            mock.onGet(`${urls.validate}/2290-9009-2990-9099`).reply(200, mockedResponse);
    
            jest.spyOn(axiosInstance, "get").mockImplementation(() => Promise.resolve(mockedResponse))

            const result = await validateToken('2290-9009-2990-9099');
            expect(result.data).toEqual(mockedResponse.data)
        });

        it('Generate token should return error when the input is wrong', async () => {
       
            const errorResponse = {
                success: false,
                data: null,
                error: null,
                message: "Token is invalid",
                status: 400
            }

            mock.onGet(`${urls.validate}/2290-9009-2990-9091`).reply(400, errorResponse);
         
            jest.spyOn(axiosInstance, "get").mockImplementation(() => Promise.reject(errorResponse))

            const result = await validateToken('2290-9009-2990-9091');
            expect(result.data).toEqual(errorResponse.data)
        });

    })

})