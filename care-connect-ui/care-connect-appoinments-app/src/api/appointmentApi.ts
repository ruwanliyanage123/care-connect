const APPOINTMENT_API_URL = "http://localhost:8081/api/v1/appointments";

export interface Appointment {
    id: number;
    patient: string;
    consultant: string;
    date: string;   // formatted date (YYYY-MM-DD)
    time: string;   // formatted time (e.g. "10:00 AM")
    status: string; // Upcoming / Completed / Cancelled etc.
}

export interface CreateAppointmentRequest {
    patientId: number;
    consultantId: number;
    date: string;
    time: string;
    type: string;
}

// Small helper to safely format date/time if backend sends ISO string
function splitDateTime(dateTime: string | null | undefined): { date: string; time: string } {
    if (!dateTime) {
        return { date: "-", time: "-" };
    }

    try {
        const d = new Date(dateTime);
        const date = d.toISOString().slice(0, 10); // YYYY-MM-DD

        const time = d.toLocaleTimeString("en-US", {
            hour: "2-digit",
            minute: "2-digit",
        });

        return { date, time };
    } catch {
        return { date: dateTime, time: "" };
    }
}

/**
 * Map raw backend object → UI Appointment object.
 * This is defensive so it works even if field names differ slightly.
 */
function mapAppointment(raw: any): Appointment {
    // Try common field name patterns – adjust here if your backend differs
    const patient =
        raw.patient ||
        raw.patientName ||
        `${raw.patientFirstName ?? ""} ${raw.patientLastName ?? ""}`.trim() ||
        "Unknown Patient";

    const consultant =
        raw.consultant ||
        raw.consultantName ||
        `${raw.consultantFirstName ?? ""} ${raw.consultantLastName ?? ""}`.trim() ||
        "Unknown Consultant";

    // If backend has combined dateTime or separate date/time fields
    let date = raw.date ?? raw.appointmentDate ?? "";
    let time = raw.time ?? raw.appointmentTime ?? "";

    if (!date && raw.dateTime) {
        const { date: d, time: t } = splitDateTime(raw.dateTime);
        date = d;
        time = t;
    } else if (!date && raw.appointmentDateTime) {
        const { date: d, time: t } = splitDateTime(raw.appointmentDateTime);
        date = d;
        time = t;
    }

    const status =
        raw.status ||
        raw.appointmentStatus ||
        "UNKNOWN";

    return {
        id: raw.id,
        patient,
        consultant,
        date: date || "-",
        time: time || "-",
        status,
    };
}

export async function getAllAppointments(): Promise<Appointment[]> {
    const response = await fetch(APPOINTMENT_API_URL);

    if (!response.ok) {
        throw new Error("Failed to fetch appointments");
    }

    const data = await response.json();

    if (!Array.isArray(data)) {
        return [];
    }

    return data.map(mapAppointment);
}


export async function createAppointment(req: CreateAppointmentRequest) {
    const res = await fetch(APPOINTMENT_API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(req)
    });

    if (!res.ok) {
        throw new Error("Failed to create appointment");
    }

    return res.json();
}
