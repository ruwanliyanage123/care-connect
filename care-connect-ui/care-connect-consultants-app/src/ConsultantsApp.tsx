import React, { useState } from "react";
import "./global.css";

interface Consultant {
    id: number;
    name: string;
    specialty: string;
    experience: number;
    contact: string;
    availability: string;
}

const dummyConsultants: Consultant[] = [
    { id: 1, name: "Dr. Amanda Silva", specialty: "Cardiologist", experience: 12, contact: "0771234567", availability: "Available" },
    { id: 2, name: "Dr. Kasun Perera", specialty: "Dermatologist", experience: 8, contact: "0759876543", availability: "Unavailable" },
    { id: 3, name: "Dr. Nethmi Fernando", specialty: "Pediatrician", experience: 15, contact: "0717654321", availability: "Available" },
    { id: 4, name: "Dr. Samitha Jayawardena", specialty: "Neurologist", experience: 10, contact: "0776543210", availability: "Unavailable" },
];

const ConsultantsApp: React.FC = () => {
    const [search, setSearch] = useState("");

    const filtered = dummyConsultants.filter((c) =>
        c.name.toLowerCase().includes(search.toLowerCase())
    );

    return (
        <div className="p-6 w-full">
            {/* Page Header */}
            <h1 className="text-3xl font-bold mb-4 text-gray-800">Consultants</h1>

            {/* Search */}
            <div className="mb-6">
                <input
                    type="text"
                    placeholder="Search consultant by name..."
                    className="w-full md:w-1/3 px-4 py-2 border rounded-xl shadow-sm focus:ring focus:ring-blue-200"
                    value={search}
                    onChange={(e) => setSearch(e.target.value)}
                />
            </div>

            {/* Stats Summary */}
            <div className="grid grid-cols-1 md:grid-cols-3 gap-4 mb-6">
                <div className="bg-blue-100 p-5 rounded-2xl shadow-md">
                    <p className="text-sm text-gray-600">Total Consultants</p>
                    <h2 className="text-3xl font-bold">{dummyConsultants.length}</h2>
                </div>

                <div className="bg-green-100 p-5 rounded-2xl shadow-md">
                    <p className="text-sm text-gray-600">Available</p>
                    <h2 className="text-3xl font-bold">
                        {dummyConsultants.filter((c) => c.availability === "Available").length}
                    </h2>
                </div>

                <div className="bg-red-100 p-5 rounded-2xl shadow-md">
                    <p className="text-sm text-gray-600">Unavailable</p>
                    <h2 className="text-3xl font-bold">
                        {dummyConsultants.filter((c) => c.availability === "Unavailable").length}
                    </h2>
                </div>
            </div>

            {/* Consultants Table */}
            <div className="bg-white shadow-xl rounded-2xl overflow-hidden border border-gray-200">
                <table className="min-w-full table-auto">
                    <thead className="bg-gray-50">
                    <tr>
                        <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">ID</th>
                        <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">Name</th>
                        <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">Specialty</th>
                        <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">Experience</th>
                        <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">Contact</th>
                        <th className="px-6 py-3 text-left text-xs font-semibold text-gray-600 uppercase tracking-wider">Availability</th>
                    </tr>
                    </thead>

                    <tbody className="text-sm text-gray-700">
                    {filtered.length === 0 ? (
                        <tr>
                            <td colSpan={6} className="text-center py-6 text-gray-500">
                                No consultants found.
                            </td>
                        </tr>
                    ) : (
                        filtered.map((c, index) => (
                            <tr
                                key={c.id}
                                className={`border-b transition-all duration-200 
                    ${index % 2 === 0 ? "bg-white" : "bg-gray-50"}
                    hover:bg-blue-50 hover:cursor-pointer`}
                            >
                                <td className="px-6 py-4 font-medium text-gray-800">{c.id}</td>
                                <td className="px-6 py-4 font-semibold">{c.name}</td>
                                <td className="px-6 py-4">{c.specialty}</td>
                                <td className="px-6 py-4">{c.experience} years</td>
                                <td className="px-6 py-4">{c.contact}</td>

                                <td className="px-6 py-4">
                    <span
                        className={`px-3 py-1 rounded-full text-xs font-semibold
                        ${
                            c.availability === "Available"
                                ? "bg-green-100 text-green-800"
                                : "bg-red-100 text-red-800"
                        }
                      `}
                    >
                      {c.availability}
                    </span>
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

export default ConsultantsApp;
