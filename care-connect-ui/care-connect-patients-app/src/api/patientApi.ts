// ===============================
// patientApi.ts
// ===============================

export interface Patient {
    id: number;
    name: string;
    age: number;
    gender: string;
    contact: string;

    // FULL OBJECT (used for editing)
    raw: BackendPatientResponse;
}

// This is exactly what backend returns
export interface BackendPatientResponse {
    id: number;
    firstName: string;
    lastName: string;
    dateOfBirth: string;
    gender: string;
    email: string;
    phone: string;
    nationalId: string;
    addressLine1: string;
    addressLine2: string;
    city: string;
    country: string;
    bloodGroup: string;
    allergies: string;
    chronicDiseases: string;
    emergencyContactName: string;
    emergencyContactPhone: string;
    createdAt: string;
    updatedAt: string;
}

// What backend expects when creating/updating
export interface BackendPatientPayload {
    firstName: string;
    lastName: string;
    dateOfBirth: string;
    gender: string;
    email: string;
    phone: string;
    nationalId: string;
    addressLine1: string;
    addressLine2: string;
    city: string;
    country: string;
    bloodGroup: string;
    allergies: string;
    chronicDiseases: string;
    emergencyContactName: string;
    emergencyContactPhone: string;
}

const PATIENT_API_URL = "http://localhost:8085/api/v1/patients";

// Convert backend → UI Patient model
function convertToPatientModel(p: BackendPatientResponse): Patient {
    return {
        id: p.id,
        name: `${p.firstName} ${p.lastName}`,
        age: calculateAge(p.dateOfBirth),
        gender: p.gender,
        contact: p.phone,
        raw: p
    };
}

export async function getAllPatients(): Promise<Patient[]> {
    const res = await fetch(PATIENT_API_URL);
    if (!res.ok) throw new Error("Failed to fetch patients");

    const data: BackendPatientResponse[] = await res.json();
    return data.map(convertToPatientModel);
}

export async function createPatient(payload: BackendPatientPayload) {
    const res = await fetch(PATIENT_API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload)
    });
    if (!res.ok) throw new Error("Failed to create patient");
}

export async function updatePatient(id: number, payload: BackendPatientPayload) {
    const res = await fetch(`${PATIENT_API_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload)
    });
    if (!res.ok) throw new Error("Failed to update patient");
}

export async function deletePatient(id: number) {
    const res = await fetch(`${PATIENT_API_URL}/${id}`, { method: "DELETE" });
    if (!res.ok) throw new Error("Failed to delete patient");
}

function calculateAge(date: string): number {
    if (!date) return 0;
    const dob = new Date(date);
    const diff = Date.now() - dob.getTime();
    return Math.floor(diff / (1000 * 60 * 60 * 24 * 365.25));
}
