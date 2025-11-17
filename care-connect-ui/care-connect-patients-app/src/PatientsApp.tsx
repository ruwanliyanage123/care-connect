import React, { useState } from "react";
import "./global.css";

interface Patient {
    id: number;
    name: string;
    age: number;
    gender: string;
    contact: string;
    status: string;
}

const dummyPatients: Patient[] = [
    { id: 1, name: "John Doe", age: 34, gender: "Male", contact: "0771234567", status: "Active" },
    { id: 2, name: "Jane Smith", age: 28, gender: "Female", contact: "0759876543", status: "Active" },
    { id: 3, name: "Michael Lee", age: 45, gender: "Male", contact: "0717654321", status: "Inactive" },
];

const PatientsApp: React.FC = () => {
    const [search, setSearch] = useState("");

    const filtered = dummyPatients.filter((p) =>
        p.name.toLowerCase().includes(search.toLowerCase())
    );

    return (
        <div className="p-6 w-full">
            {/* Header */}
            <h1 className="text-3xl font-bold mb-4 text-gray-800">Patients</h1>

            {/* Search Input */}
            <div className="mb-6">
                <input
                    type="text"
                    placeholder="Search patients by name..."
                    className="w-full md:w-1/3 px-4 py-2 border rounded-xl shadow-sm focus:ring focus:ring-blue-200"
                    value={search}
                    onChange={(e) => setSearch(e.target.value)}
                />
            </div>

            {/* Stats Section */}
            <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-6">
                <div className="bg-blue-100 p-4 rounded-xl shadow">
                    <p className="text-sm text-gray-700">Total Patients</p>
                    <h2 className="text-2xl font-bold">{dummyPatients.length}</h2>
                </div>
                <div className="bg-green-100 p-4 rounded-xl shadow">
                    <p className="text-sm text-gray-700">Active</p>
                    <h2 className="text-2xl font-bold">
                        {dummyPatients.filter((p) => p.status === "Active").length}
                    </h2>
                </div>
                <div className="bg-red-100 p-4 rounded-xl shadow">
                    <p className="text-sm text-gray-700">Inactive</p>
                    <h2 className="text-2xl font-bold">
                        {dummyPatients.filter((p) => p.status === "Inactive").length}
                    </h2>
                </div>
            </div>

            {/* Patient Table */}
            <div className="bg-white shadow rounded-xl overflow-hidden">
                <table className="min-w-full table-auto">
                    <thead className="bg-gray-100">
                    <tr>
                        <th className="px-4 py-2 text-left text-sm font-semibold text-gray-700">ID</th>
                        <th className="px-4 py-2 text-left text-sm font-semibold text-gray-700">Name</th>
                        <th className="px-4 py-2 text-left text-sm font-semibold text-gray-700">Age</th>
                        <th className="px-4 py-2 text-left text-sm font-semibold text-gray-700">Gender</th>
                        <th className="px-4 py-2 text-left text-sm font-semibold text-gray-700">Contact</th>
                        <th className="px-4 py-2 text-left text-sm font-semibold text-gray-700">Status</th>
                    </tr>
                    </thead>
                    <tbody>
                    {filtered.length === 0 ? (
                        <tr>
                            <td colSpan={6} className="text-center py-4 text-gray-500">
                                No patients found.
                            </td>
                        </tr>
                    ) : (
                        filtered.map((p) => (
                            <tr
                                key={p.id}
                                className="border-b hover:bg-gray-50 transition-all"
                            >
                                <td className="px-4 py-2">{p.id}</td>
                                <td className="px-4 py-2 font-medium">{p.name}</td>
                                <td className="px-4 py-2">{p.age}</td>
                                <td className="px-4 py-2">{p.gender}</td>
                                <td className="px-4 py-2">{p.contact}</td>
                                <td
                                    className={`px-4 py-2 font-semibold ${
                                        p.status === "Active" ? "text-green-700" : "text-red-700"
                                    }`}
                                >
                                    {p.status}
                                </td>
                            </tr>
                        ))
                    )}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default PatientsApp;
