export interface Patient {
    id?: number;
    name: string;
    age: number;
    gender: string;
    contact: string;
}

const BASE_URL = "http://localhost:8085/api/v1/patients";

export const getAllPatients = async () => {
    const res = await fetch(BASE_URL);
    if (!res.ok) throw new Error("Failed to fetch patients");
    return res.json();
};

export const createPatient = async (patient: Patient) => {
    const res = await fetch(BASE_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(patient),
    });
    if (!res.ok) throw new Error("Failed to create patient");
    return res.json();
};

export const updatePatient = async (id: number, patient: Patient) => {
    const res = await fetch(`${BASE_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(patient),
    });
    if (!res.ok) throw new Error("Failed to update patient");
    return res.json();
};

export const deletePatient = async (id: number) => {
    const res = await fetch(`${BASE_URL}/${id}`, {
        method: "DELETE",
    });
    if (!res.ok) throw new Error("Failed to delete patient");
};
