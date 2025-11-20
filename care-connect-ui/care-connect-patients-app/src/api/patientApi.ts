export interface Patient {
    id: number;
    name: string;
    age: number;
    gender: string;
    contact: string;
}

const PATIENT_API_URL = "http://localhost:8085/api/v1/patients";

function calculateAge(dob: string): number {
    const birth = new Date(dob);
    const diff = Date.now() - birth.getTime();
    return new Date(diff).getUTCFullYear() - 1970;
}

export async function getAllPatients(): Promise<Patient[]> {
    const response = await fetch(PATIENT_API_URL);
    if (!response.ok) {
        throw new Error("Failed to fetch patients");
    }
    const data = await response.json();
    return data.map((p: any) => ({
        id: p.id,
        name: `${p.firstName} ${p.lastName}`,
        age: calculateAge(p.dateOfBirth),
        gender: p.gender,
        contact: p.phone,
    }));
}
