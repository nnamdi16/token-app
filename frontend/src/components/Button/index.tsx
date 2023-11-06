import styles from "./Button.module.scss";
import { ButtonProps } from "./types";

const Button = ({ isDisabled, text, onClick }: ButtonProps) => {
  return (
    <button
      disabled={isDisabled}
      onClick={onClick}
      className={
        isDisabled ? styles["container__disabled"] : styles["container"]
      }
    >
      {text}
    </button>
  );
};

export default Button;
