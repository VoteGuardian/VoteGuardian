'use client'
import VoteCandidate from "@/components/VoteCandidate/VoteCandidate";
import VoteMenu from "@/components/VoteMenu/VoteMenu";
import { RecoilRoot } from "recoil";
import './Candidate.scss'

export default function Candidate() {
    return(
        <>
        <RecoilRoot>
            <div className="vote-candidate-flex">
                <VoteMenu/>
                <VoteCandidate/>
            </div>
        </RecoilRoot>
        </>
    )
}