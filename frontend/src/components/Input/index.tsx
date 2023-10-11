import { InputProps } from "./types";
import styles from './Input.module.scss';

const Input=({onChange,placeholder,value}:InputProps)=>{

    return <input value={value} className={styles.container} type='text' placeholder={placeholder} onChange={onChange} />
}

export default Input;