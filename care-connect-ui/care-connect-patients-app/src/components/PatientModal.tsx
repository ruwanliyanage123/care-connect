import React, { useState, useEffect } from "react";
import { Patient } from "../api/patientApi";
import "../global.css";

interface Props {
    isOpen: boolean;
    onClose: () => void;
    onSubmit: (patient: Patient) => void;
    initialData?: Patient | null;
}

const PatientModal: React.FC<Props> = ({ isOpen, onClose, onSubmit, initialData }) => {
    const [form, setForm] = useState<Patient>({
        name: "",
        age: 0,
        gender: "",
        contact: "",
    });

    useEffect(() => {
        if (initialData) setForm(initialData);
    }, [initialData]);

    if (!isOpen) return null;

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = () => {
        onSubmit(form);
        onClose();
    };

    return (
        <div className="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center z-50">
            <div className="bg-white p-6 rounded-xl shadow-lg w-full max-w-md">
                <h2 className="text-xl font-bold mb-4">
                    {initialData ? "Edit Patient" : "Add Patient"}
                </h2>

                <div className="space-y-3">

                    <input
                        name="name"
                        value={form.name}
                        onChange={handleChange}
                        placeholder="Name"
                        className="w-full border p-2 rounded"
                    />

                    <input
                        name="age"
                        type="number"
                        value={form.age}
                        onChange={handleChange}
                        placeholder="Age"
                        className="w-full border p-2 rounded"
                    />

                    <input
                        name="gender"
                        value={form.gender}
                        onChange={handleChange}
                        placeholder="Gender"
                        className="w-full border p-2 rounded"
                    />

                    <input
                        name="contact"
                        value={form.contact}
                        onChange={handleChange}
                        placeholder="Contact"
                        className="w-full border p-2 rounded"
                    />

                </div>

                <div className="flex justify-end gap-2 mt-4">
                    <button onClick={onClose} className="px-4 py-2 border rounded">
                        Cancel
                    </button>
                    <button
                        onClick={handleSubmit}
                        className="px-4 py-2 bg-healthcare-primary text-white rounded hover:bg-healthcare-primaryDark"
                    >
                        Save
                    </button>
                </div>
            </div>
        </div>
    );
};

export default PatientModal;
