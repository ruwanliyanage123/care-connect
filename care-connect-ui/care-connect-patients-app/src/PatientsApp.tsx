import React, { useEffect, useState } from "react";
import {
    getAllPatients,
    createPatient,
    updatePatient,
    deletePatient,
    Patient
} from "./api/patientApi";
import './global.css'

import PatientModal from "./components/PatientModal";

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
        } catch (err) {
            setError("Failed to load patients.");
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        loadPatients();
    }, []);

    const filtered = patients.filter((p) =>
        (p.name ?? "").toLowerCase().includes(search.toLowerCase())
    );

    const handleCreate = async (patient: Patient) => {
        await createPatient(patient);
        loadPatients();
    };

    const handleEdit = async (patient: Patient) => {
        if (!editData?.id) return;
        await updatePatient(editData.id, patient);
        loadPatients();
    };

    const handleDelete = async (id: number) => {
        if (!confirm("Delete this patient?")) return;
        await deletePatient(id);
        loadPatients();
    };

    return (
        <div className="p-6 w-full">

            {/* Header */}
            <h1 className="text-3xl font-bold mb-4 text-gray-800">Patients</h1>

            {/* Search + Add Button */}
            <div className="flex flex-col md:flex-row md:items-center md:justify-between gap-4 mb-6">

                <input
                    type="text"
                    placeholder="Search patients by name..."
                    className="w-full md:w-1/3 px-4 py-2 border rounded-xl shadow-sm"
                    value={search}
                    onChange={(e) => setSearch(e.target.value)}
                />

                <button
                    onClick={() => { setEditData(null); setModalOpen(true); }}
                    className="px-4 py-2 bg-healthcare-primary text-white rounded-lg hover:bg-healthcare-primaryDark"
                >
                    + Add Patient
                </button>
            </div>

            {loading && <p>Loading...</p>}
            {error && <p className="text-red-500">{error}</p>}

            {/* Table */}
            <div className="bg-white shadow-xl rounded-2xl overflow-hidden border border-gray-200">
                <table className="min-w-full">
                    <thead className="bg-gray-50">
                    <tr>
                        <th className="px-6 py-3">ID</th>
                        <th className="px-6 py-3">Name</th>
                        <th className="px-6 py-3">Age</th>
                        <th className="px-6 py-3">Gender</th>
                        <th className="px-6 py-3">Contact</th>
                        <th className="px-6 py-3">Actions</th>
                    </tr>
                    </thead>

                    <tbody>
                    {filtered.map((p, index) => (
                        <tr
                            key={p.id}
                            className={`border-b ${index % 2 === 0 ? "bg-white" : "bg-gray-50"}`}
                        >
                            <td className="px-6 py-4">{p.id}</td>
                            <td className="px-6 py-4">{p.name}</td>
                            <td className="px-6 py-4">{p.age}</td>
                            <td className="px-6 py-4">{p.gender}</td>
                            <td className="px-6 py-4">{p.contact}</td>

                            <td className="px-6 py-4 flex gap-3">
                                <button
                                    onClick={() => { setEditData(p); setModalOpen(true); }}
                                    className="text-blue-600 hover:underline"
                                >
                                    Edit
                                </button>

                                <button
                                    onClick={() => handleDelete(p.id!)}
                                    className="text-red-600 hover:underline"
                                >
                                    Delete
                                </button>
                            </td>
                        </tr>
                    ))}

                    {filtered.length === 0 && (
                        <tr>
                            <td colSpan={6} className="text-center py-6 text-gray-500">
                                No patients found.
                            </td>
                        </tr>
                    )}
                    </tbody>
                </table>
            </div>

            {/* Modal */}
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
