
import axios from 'axios';

export const client=(url:string)=>axios.create(
    {
        baseURL: url ,
        headers: {
            'Content-Type': 'application/json', 
            'Authorization': 'Bearer yourAccessToken',
        },
        timeout: 3000
      }
)