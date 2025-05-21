import { ChangeEvent } from "react";

type FormInputProps = {
  htmlFor: string;
  header: string;
  inputType: string;
  inputName: string;
  value: string | number;
  onChange: (e: ChangeEvent<HTMLInputElement>) => void;
  placeholder?: string;
}

const FormInput: React.FC<FormInputProps> = ({
  htmlFor,
  header,
  inputType,
  inputName,
  value,
  onChange,
  placeholder,
}) => {
  return (
    <div>
      <label
        htmlFor={htmlFor}
        className="block mb-2 text-sm font-medium"
        style={{ color: "var(--primary-color" }}
      >
        {header}
      </label>
      <input
        type={inputType}
        name={inputName}
        id={htmlFor}
        value={value}
        onChange={onChange}
        className="bg-green-100 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5"
        placeholder={placeholder}
        required
      />
    </div>
  );
};

export default FormInput;
