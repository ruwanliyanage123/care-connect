// ===============================
// PatientModal.tsx
// ===============================

import React, { useState, useEffect } from "react";
import { BackendPatientPayload, Patient } from "../api/patientApi";
import "../global.css";

interface Props {
    isOpen: boolean;
    onClose: () => void;
    onSubmit: (data: BackendPatientPayload) => void;
    initialData?: Patient | null;
}

const emptyForm: BackendPatientPayload = {
    firstName: "",
    lastName: "",
    dateOfBirth: "",
    gender: "",
    email: "",
    phone: "",
    nationalId: "",
    addressLine1: "",
    addressLine2: "",
    city: "",
    country: "",
    bloodGroup: "",
    allergies: "",
    chronicDiseases: "",
    emergencyContactName: "",
    emergencyContactPhone: ""
};

const PatientModal: React.FC<Props> = ({
                                           isOpen,
                                           onClose,
                                           onSubmit,
                                           initialData
                                       }) => {
    const [form, setForm] =
        useState<BackendPatientPayload>(emptyForm);

    useEffect(() => {
        if (initialData) {
            const p = initialData.raw;
            setForm({
                firstName: p.firstName,
                lastName: p.lastName,
                dateOfBirth: p.dateOfBirth,
                gender: p.gender,
                email: p.email,
                phone: p.phone,
                nationalId: p.nationalId,
                addressLine1: p.addressLine1,
                addressLine2: p.addressLine2,
                city: p.city,
                country: p.country,
                bloodGroup: p.bloodGroup,
                allergies: p.allergies,
                chronicDiseases: p.chronicDiseases,
                emergencyContactName: p.emergencyContactName,
                emergencyContactPhone: p.emergencyContactPhone
            });
        } else {
            setForm(emptyForm);
        }
    }, [initialData]);

    if (!isOpen) return null;

    const handleChange =
        (e: React.ChangeEvent<HTMLInputElement>) => {
            setForm({ ...form, [e.target.name]: e.target.value });
        };

    const handleSubmit = () => {
        onSubmit(form);
        onClose();
    };

    return (
        <div className="fixed inset-0 bg-black bg-opacity-40 flex items-center justify-center z-50">
            <div className="bg-white p-6 rounded-xl shadow-lg w-full max-w-2xl max-h-[90vh] overflow-y-auto">

                <h2 className="text-xl font-bold mb-4 text-healthcare-primary">
                    {initialData ? "Edit Patient" : "Add Patient"}
                </h2>

                <div className="grid grid-cols-1 md:grid-cols-2 gap-4">

                    {Object.keys(emptyForm).map((key) => (
                        <input
                            key={key}
                            name={key}
                            value={(form as any)[key]}
                            onChange={handleChange}
                            placeholder={key.replace(/([A-Z])/g, " $1")}
                            className="border p-2 rounded"
                            type={key === "dateOfBirth" ? "date" : "text"}
                        />
                    ))}

                </div>

                <div className="flex justify-end gap-3 mt-6">
                    <button
                        onClick={onClose}
                        className="px-4 py-2 border rounded hover:bg-gray-100"
                    >
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
