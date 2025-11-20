export interface Consultant {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    phone: string;
    gender: string;
    specialization: string;
    yearsOfExperience: number;
    registrationNumber: string;
    languages: string;
    bio: string;
    rating: number;
    active: boolean;
}

const CONSULTANT_API_URL = "http://localhost:8082/api/v1/consultants";
export async function getAllConsultants(): Promise<Consultant[]> {
    const res = await fetch(CONSULTANT_API_URL);
    if (!res.ok) {
        throw new Error("Failed to fetch consultants");
    }
    return res.json();
}
