// ===================================
// PatientsApp.tsx
// ===================================
import React, { useEffect, useState } from "react";
import {
    getAllPatients,
    createPatient,
    updatePatient,
    deletePatient,
    Patient,
    BackendPatientPayload
} from "./api/patientApi";

import PatientModal from "./components/PatientModal";
import "./global.css";

const PatientsApp: React.FC = () => {
    const [patients, setPatients] = useState<Patient[]>([]);
    const [search, setSearch] = useState("");
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const [modalOpen, setModalOpen] = useState(false);
    const [editData, setEditData] = useState<Patient | null>(null);

    const loadPatients = async () => {
        try {
            setLoading(true);
            const data = await getAllPatients();
            setPatients(data);
        } catch {
            setError("Failed to load patients.");
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        loadPatients();
    }, []);

    const filtered = patients.filter((p) =>
        p.name.toLowerCase().includes(search.toLowerCase())
    );

    const handleCreate = async (data: BackendPatientPayload) => {
        await createPatient(data);
        loadPatients();
    };

    const handleEdit = async (data: BackendPatientPayload) => {
        if (!editData) return;
        await updatePatient(editData.id, data);
        loadPatients();
    };

    const handleDelete = async (id: number) => {
        if (!confirm("Delete this patient?")) return;
        await deletePatient(id);
        loadPatients();
    };

    return (
        <div className="p-6 w-full">

            <h1 className="text-3xl font-bold mb-4 text-gray-800">Patients</h1>

            <div className="flex flex-col md:flex-row md:items-center md:justify-between gap-4 mb-6">

                <input
                    type="text"
                    placeholder="Search patients..."
                    className="w-full md:w-1/3 px-4 py-2 border rounded-xl shadow-sm focus:ring"
                    value={search}
                    onChange={(e) => setSearch(e.target.value)}
                />

                <button
                    className="px-4 py-2 bg-healthcare-primary text-white rounded-lg hover:bg-healthcare-primaryDark"
                    onClick={() => { setEditData(null); setModalOpen(true); }}
                >
                    + Add Patient
                </button>

            </div>

            {loading && <p>Loading...</p>}
            {error && <p className="text-red-500">{error}</p>}

            <div className="bg-white shadow-xl rounded-2xl border border-gray-200 overflow-x-auto">

                <table className="min-w-full text-sm text-gray-700">
                    <thead className="bg-gray-100 text-gray-600">
                    <tr>
                        <th className="px-6 py-3 text-left font-semibold">ID</th>
                        <th className="px-6 py-3 text-left font-semibold">Name</th>
                        <th className="px-6 py-3 text-left font-semibold">Age</th>
                        <th className="px-6 py-3 text-left font-semibold">Gender</th>
                        <th className="px-6 py-3 text-left font-semibold">Contact</th>
                        <th className="px-6 py-3 text-left font-semibold min-w-[170px]">
                            Actions
                        </th>
                    </tr>
                    </thead>

                    <tbody>
                    {filtered.map((p, index) => (
                        <tr
                            key={p.id}
                            className={`${index % 2 === 0
                                ? "bg-white"
                                : "bg-gray-50"} border-b`}
                        >
                            <td className="px-6 py-4">{p.id}</td>
                            <td className="px-6 py-4">{p.name}</td>
                            <td className="px-6 py-4">{p.age}</td>
                            <td className="px-6 py-4">{p.gender}</td>
                            <td className="px-6 py-4">{p.contact}</td>

                            <td className="px-6 py-4 flex gap-3">

                                <button
                                    className="px-4 py-1.5 bg-healthcare-primary text-white rounded-md hover:bg-healthcare-primaryDark transition"
                                    onClick={() => { setEditData(p); setModalOpen(true); }}
                                >
                                    Edit
                                </button>

                                <button
                                    className="px-4 py-1.5 bg-healthcare-danger text-white rounded-md hover:bg-red-700 transition"
                                    onClick={() => handleDelete(p.id)}
                                >
                                    Delete
                                </button>

                            </td>
                        </tr>
                    ))}
                    </tbody>

                </table>
            </div>

            <PatientModal
                isOpen={modalOpen}
                onClose={() => setModalOpen(false)}
                onSubmit={editData ? handleEdit : handleCreate}
                initialData={editData}
            />

        </div>
    );
};

export default PatientsApp;
